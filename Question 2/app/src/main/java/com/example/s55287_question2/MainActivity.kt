package com.example.s55287_question2

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.openOrCreateDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.File
import android.content.SharedPreferences




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(!dbExist(this,"myData")){
            createDB()
        }

        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        btnSubmit.setOnClickListener(){
            val username = findViewById<EditText>(R.id.inUsername)
            val password = findViewById<EditText>(R.id.inPassword)
            val uname = username.text.toString()
            val pass = password.text.toString()
            val status = CheckUser(uname,pass)
            val sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE)

            if(!status){
                val editor: SharedPreferences.Editor = sharedPref.edit()
                editor.putString("key","success")
                editor.commit()

                val intent = Intent(this,SecondActivity::class.java).apply {
                    putExtra("username", uname)
                }
                startActivity(intent)
            }else{
                subToast("User not register!")
            }

        }
    }

    private fun CheckUser(username:String,password:String):Boolean{
        val db = openOrCreateDatabase("myData", MODE_PRIVATE,null)
        val sql = "SELECT * FROM user WHERE username = '$username' & password = '$password'"
        val cursor = db.rawQuery(sql,null)
        var out = false
        if (cursor.count > 0)
            out = true
        return out
    }

    private fun dbExist(c: Context, dbName:String ):Boolean{
        val dbFile: File = c.getDatabasePath(dbName)
        return dbFile.exists()
    }

    private fun createDB(){
        val db = openOrCreateDatabase("myData", MODE_PRIVATE,null)
        subToast("Database myData created!")
        val sqlText = "CREATE TABLE IF NOT EXISTS user " +
                "(username VARCHAR(30) PRIMARY KEY, " +
                "password VARCHAR(30) NOT NULL " +
                ");"
        subToast("Table user created!")
        db.execSQL(sqlText)
        var nextSQL = "INSERT INTO user (username,password) VALUES ('ahmad','ahmad1234');"
        db.execSQL(nextSQL)
    }

    private fun subToast(msg: String){
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()
    }
}

