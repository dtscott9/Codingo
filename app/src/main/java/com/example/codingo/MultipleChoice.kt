package com.example.codingo

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView



class MultipleChoice : AppCompatActivity() {
    // Initialize variables
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
    lateinit var sitting : ImageView
    lateinit var correct : ImageView
    lateinit var sad_animation: AnimationDrawable
    lateinit var correctAnimation: AnimationDrawable



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiple_choice)

        // Get values from previous intent
        val prevIntent:Intent = getIntent()
        val lessonNumber = prevIntent.getIntExtra("lessonNumber", 1)
        val moduleName = prevIntent.getStringExtra("lessonName")

        // Change the questions loaded based on the lessonNumber
        val question = when (lessonNumber) {
            1 -> resources.getStringArray(R.array.module_1_questions)
            2 ->  resources.getStringArray(R.array.module_2_questions)
            5 -> resources.getStringArray(R.array.module_5_questions)
            else ->  resources.getStringArray(R.array.module_1_questions)
        }
        // Set lives to 5
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
        gameOver.setBackgroundResource(R.drawable.sad_animation)
        sad_animation = gameOver.background as AnimationDrawable
        sitting = findViewById(R.id.dingoSitting)
        correct = findViewById(R.id.correct)
        correct.setBackgroundResource(R.drawable.dingo_idle_animation)
        correctAnimation = correct.background as AnimationDrawable

//        menuButton.visibility = View.GONE

        gameOver.visibility = View.GONE

        // Home button
        menuButton.setOnClickListener {
            val intent = Intent(this, Welcome::class.java)
            startActivity(intent)
            finish()
        }


        lessonName.text = getString(R.string.lesson_1_name) + ": " + getString(R.string.lesson_1_title)

        // Load the questions based on the lessonNumber
        fun loadQuestions(questionNumber : Int, lessonNumber: Int, question: Array<String>) {
            correct.visibility = View.GONE
            sitting.visibility = View.VISIBLE
            validityOutput.text = ""
            continueButton.visibility = View.GONE
            backButton.visibility = View.GONE
            choice1.visibility = View.VISIBLE
            choice2.visibility = View.VISIBLE
            choice3.visibility = View.VISIBLE
            choice4.visibility = View.VISIBLE

            val questionIndex = questionNumber * 6
            lessonName.text = "Module " + lessonNumber.toString()

            // Set the text values of each answer choice button
            questionOutput.text = question[questionIndex]
            choice1.text = question[questionIndex + 1]
            choice2.text = question[questionIndex + 2]
            choice3.text = question[questionIndex + 3]
            choice4.text = question[questionIndex + 4]
            val answer : String = question[questionIndex + 5]
            fun checkValidity(button: Button) {
                // If correct
                if (button.text == answer) {
                    sitting.visibility = View.GONE
                    correct.visibility = View.VISIBLE
                    validityOutput.text = "That is correct!"
                    choice1.visibility = View.GONE
                    choice2.visibility = View.GONE
                    choice3.visibility = View.GONE
                    choice4.visibility = View.GONE
                    continueButton.visibility = View.VISIBLE
                    backButton.visibility = View.VISIBLE
                } else {
                // If incorrect
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

                        sitting.visibility = View.GONE
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

            // Set on click listeners for each answer choice
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
        loadQuestions(questionNumber, lessonNumber, question)
        // Click listeners for back and continue buttons
        continueButton.setOnClickListener {

            if (questionNumber >= ((question.size / 6) - 1)) {
                // If last question then go to home page
                val intent = Intent(this, Welcome::class.java)
                startActivity((intent))
                finish()
            } else {
                questionNumber += 1
                loadQuestions(questionNumber, lessonNumber, question)
            }

        }
        backButton.setOnClickListener {
            if (questionNumber <= 0) {
                // If first question then go to map page
                val intent = Intent(this, Map::class.java)
                intent.putExtra("lessonNumber", lessonNumber)
                intent.putExtra("lessonName", moduleName)
                startActivity(intent)
                finish()
            } else {
                questionNumber -= 1
                loadQuestions(questionNumber, lessonNumber, question)
            }

        }





    }
    // Animations
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        sad_animation.start()
        correctAnimation.start()
    }
}