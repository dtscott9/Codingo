package com.example.codingo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView



class MultipleChoice : AppCompatActivity() {
    lateinit var choice1: Button
    lateinit var choice2: Button
    lateinit var choice3: Button
    lateinit var choice4: Button
    lateinit var continueButton: Button
    lateinit var backButton : Button
    lateinit var questionOutput : TextView
    lateinit var validityOutput : TextView
    lateinit var lessonName : TextView
    lateinit var livesOutput : TextView
    lateinit var menuButton: Button
    lateinit var gameOver: ImageView



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
        continueButton = findViewById(R.id.nextQuestion)
        backButton = findViewById(R.id.previousQuestion)
        validityOutput = findViewById(R.id.validityOutput)
        lessonName = findViewById(R.id.lessonName)
        livesOutput = findViewById(R.id.livesLeft)
        menuButton = findViewById(R.id.mainMenu)
        gameOver = findViewById(R.id.sadDingo)

        menuButton.visibility = View.GONE

        gameOver.visibility = View.GONE

        menuButton.setOnClickListener {
            val intent = Intent(this, Welcome::class.java)
            startActivity(intent)
            finish()
        }

        lessonName.text = getString(R.string.lesson_name) + ": " + getString(R.string.lesson_title)


        fun loadQuestions(questionNumber : Int) {

            validityOutput.text = ""
            continueButton.visibility = View.GONE
            backButton.visibility = View.GONE
            choice1.visibility = View.VISIBLE
            choice2.visibility = View.VISIBLE
            choice3.visibility = View.VISIBLE
            choice4.visibility = View.VISIBLE

            val questionIndex = questionNumber * 6

            questionOutput.text = question[questionIndex]
            choice1.text = question[questionIndex + 1]
            choice2.text = question[questionIndex + 2]
            choice3.text = question[questionIndex + 3]
            choice4.text = question[questionIndex + 4]
            val answer : String = question[questionIndex + 5]
            fun checkValidity(button: Button) {
                if (button.text == answer) {
                    validityOutput.text = "That is correct!"

                    choice1.visibility = View.GONE
                    choice2.visibility = View.GONE
                    choice3.visibility = View.GONE
                    choice4.visibility = View.GONE
                    continueButton.visibility = View.VISIBLE
                    backButton.visibility = View.VISIBLE
                } else {

                    if (lives >= 2) {
                        lives -= 1
                        livesOutput.text = "Lives: " + lives.toString()
                        validityOutput.text = "That is incorrect!"
                    }
                    else {
                        lives = 0
                        gameOver.visibility = View.VISIBLE
                        livesOutput.text = "Lives: " + lives.toString()
                        validityOutput.text = "Game Over"

                        menuButton.visibility = View.VISIBLE
                        questionOutput.visibility = View.GONE
                        choice1.visibility = View.GONE
                        choice2.visibility = View.GONE
                        choice3.visibility = View.GONE
                        choice4.visibility = View.GONE
                        continueButton.visibility = View.GONE
                        backButton.visibility = View.GONE
                        lessonName.visibility = View.GONE
                        livesOutput.visibility = View.GONE

                    }
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

        var questionNumber = 0
        loadQuestions(questionNumber)
        continueButton.setOnClickListener {

            if (questionNumber >= ((question.size / 6) - 1)) {

            } else {
                questionNumber += 1
                loadQuestions(questionNumber)
            }



        }
        backButton.setOnClickListener {
            if (questionNumber <= 0) {

            } else {
                questionNumber -= 1
                loadQuestions(questionNumber)
            }

        }





    }


}