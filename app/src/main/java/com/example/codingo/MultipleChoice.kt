package com.example.codingo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView



class MultipleChoice : AppCompatActivity() {
    lateinit var choice1: Button
    lateinit var choice2: Button
    lateinit var choice3: Button
    lateinit var choice4: Button
    lateinit var questionOutput : TextView
    lateinit var validityOutput : TextView
    lateinit var lessonName : TextView
    lateinit var livesOutput : TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiple_choice)

        val question = resources.getStringArray(R.array.module_1_questions)
        var lives = 5


        questionOutput = findViewById(R.id.question)
        choice1 = findViewById(R.id.button1)
        choice2 = findViewById(R.id.button2)
        choice3 = findViewById(R.id.button3)
        choice4 = findViewById(R.id.button4)
        validityOutput = findViewById(R.id.validityOutput)
        lessonName = findViewById(R.id.lessonName)
        livesOutput = findViewById(R.id.livesLeft)

        lessonName.text = getString(R.string.lesson_name) + ": " + getString(R.string.lesson_title)

        questionOutput.text = question[0]
        choice1.text = question[1]
        choice2.text = question[2]
        choice3.text = question[3]
        choice4.text = question[4]
        val answer : String = question[5]


        fun checkValidity(button: Button) {
            if (button.text == answer) {
                validityOutput.text = "That is correct!"
            } else {
                lives -= 1
                livesOutput.text = "Lives: " + lives.toString()
                validityOutput.text = "That is incorrect!"
            }
        }

        choice1.setOnClickListener {
            checkValidity(choice1)
        }
        choice2.setOnClickListener {
            checkValidity(choice2)
        }
        choice3.setOnClickListener {
            checkValidity(choice3)
        }
        choice4.setOnClickListener {
            checkValidity(choice4)
        }



    }


}