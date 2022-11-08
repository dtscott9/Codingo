package com.example.codingo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.view.forEach

class Welcome : AppCompatActivity() {
    private lateinit var tips: Button
    private lateinit var start: Button
    private lateinit var lessonButtons: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        start = findViewById(R.id.unitId)
        lessonButtons = findViewById(R.id.lessonButtons)
        lessonButtons.visibility = View.GONE


        start.setOnClickListener{
//
            lessonButtons.visibility = View.VISIBLE
        }

        lessonButtons.forEach { lesson ->
            if (lesson is Button) {
                lesson.setOnClickListener{
                    val lessonNumber = lesson.text[0].toString().toInt()
                    val lessonContent = lesson.text.drop(3)
                    val intent1 = Intent(this, com.example.codingo.Map::class.java)
                    intent1.putExtra("lessonNumber", lessonNumber)
                    intent1.putExtra("lessonName", lessonContent)
                    startActivity(intent1)
                    finish()
                }
            }
        }
    }
}