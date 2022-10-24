package com.example.codingo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class activity_tips : AppCompatActivity() {
    private lateinit var next: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tips)

        next = findViewById(R.id.next)
        next.setOnClickListener{
            val intent = Intent(this, practice_mchoice::class.java)
            startActivity(intent)
            finish()
        }
    }
}