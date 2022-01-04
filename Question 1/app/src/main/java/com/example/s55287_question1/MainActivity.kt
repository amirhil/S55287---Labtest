package com.example.s55287_question1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)

        btn1.setOnClickListener(){
            val web = findViewById<EditText>(R.id.inputWeb)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(web.text.toString()))
            startActivity(intent)
        }

        btn2.setOnClickListener(){
            val phone = findViewById<EditText>(R.id.inputNumber)
            val phoneNo = phone.text.toString()
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("tel:$phoneNo"))
            startActivity(intent)
        }


    }
}