package com.example.codingo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var signIn : Button;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signIn = findViewById(R.id.signIn);

        signIn.setOnClickListener {
            val intent = Intent(this, com.example.codingo.Map::class.java)
            startActivity(intent)
            finish()
        }


    }
}