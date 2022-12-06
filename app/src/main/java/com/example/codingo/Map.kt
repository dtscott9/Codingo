package com.example.codingo

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class Map : AppCompatActivity() {
    private lateinit var jeepAnimation: AnimationDrawable
    private lateinit var jeepImage: ImageView
    private lateinit var begin:Button
    private lateinit var lesson:TextView
    private lateinit var lessonHint: TextView
    private lateinit var tips:Button
    private lateinit var lessonContent: TextView
    private lateinit var homeButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        begin = findViewById(R.id.start)
        lesson = findViewById(R.id.lesson)
        tips = findViewById(R.id.tips)
        homeButton = findViewById(R.id.homeButton)
        lessonContent = findViewById(R.id.unitContent)
        lessonHint = findViewById(R.id.lessonHint)
        val prevIntent:Intent = getIntent()
        val lessonNumber = prevIntent.getIntExtra("lessonNumber", 0)
        val lessonName = prevIntent.getStringExtra("lessonName")
        lesson.text = "Lesson ${lessonNumber}:"
        lessonContent.text = lessonName

        lessonHint.text = when (lessonNumber) {
            1 -> "greeting = 'Hello World'"
            2 -> "if false: print('not true')"
            3 -> "for x in banana: print(x)"
            4 -> "def my_function(): print('Hello World')"
            5 -> "class my_class: print('Hello World')"
            else -> "greeting = 'Hello World'"
        }

        //Animation Variables
        jeepImage = findViewById(R.id.livesIcon)
        jeepImage.setBackgroundResource(R.drawable.jeep_animation)
        jeepAnimation = jeepImage.background as AnimationDrawable

        begin.setOnClickListener{
            val intent = Intent(this, MultipleChoice::class.java)
            intent.putExtra("lessonNumber", lessonNumber)
            intent.putExtra("lessonName", lessonName)
            startActivity(intent)
            finish()
        }

        tips.setOnClickListener{
            val intent1 = Intent(this, activity_tips::class.java)
            intent1.putExtra("lessonNumber", lessonNumber)
            intent1.putExtra("lessonName", lessonName)
            startActivity(intent1)
            finish()
        }

        homeButton.setOnClickListener {
            val intent2 = Intent(this, Welcome::class.java)
            startActivity(intent2)
            finish()
        }

    }
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        jeepAnimation.start()
} }