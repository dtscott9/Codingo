package com.example.codingo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Welcome : AppCompatActivity() {
    private lateinit var tips: Button
    private lateinit var start: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        tips = findViewById(R.id.tips)
        start = findViewById(R.id.unitId)

        tips.setOnClickListener{
            val intent = Intent(this, activity_tips::class.java)
            startActivity(intent)
            finish()
        }

        start.setOnClickListener{
            val intent1 = Intent(this, practice_mchoice::class.java)
            startActivity(intent1)
            finish()
        }
    }
}