package com.example.s55287_question2

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.content.SharedPreferences




class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE)

        val value = sharedPref.getString("key","")
        Log.d(TAG, value.toString());

        val intent = this.getIntent()
        val uname = intent.getStringExtra("username")

        val db = openOrCreateDatabase("myData", MODE_PRIVATE,null)
        val sql = "SELECT username,password FROM user WHERE username = '$uname'"
        val cursor = db.rawQuery(sql,null)
        var username = ""
        var password = ""

        while (cursor.moveToNext()){
            username = cursor.getString(0)
            password = cursor.getString(1)
        }
        cursor.close()

        val disUname = findViewById<TextView>(R.id.dataUsername)
        val disPass = findViewById<TextView>(R.id.dataPassword)

        disUname.setText(username)
        disPass.setText(password)

        val btnLogout = findViewById<Button>(R.id.btnLogout)

        btnLogout.setOnClickListener(){
            val editor: SharedPreferences.Editor = sharedPref.edit()
            editor.clear()
            editor.apply()
            finish()

            val intent = Intent(this,MainActivity::class.java).apply {
            }
            startActivity(intent)
        }
    }
}