package com.example.codingo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Map : AppCompatActivity() {
    private lateinit var begin:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        begin = findViewById(R.id.start)

        begin.setOnClickListener{
            val intent = Intent(this, Welcome::class.java)
            startActivity(intent)
            finish()
        }
    }
}