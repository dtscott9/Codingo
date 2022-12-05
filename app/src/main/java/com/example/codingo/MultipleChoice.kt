package com.example.codingo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiple_choice)


        val prevIntent:Intent = getIntent()
        val lessonNumber = prevIntent.getIntExtra("lessonNumber", 1)
        val moduleName = prevIntent.getStringExtra("lessonName")

        val question = when (lessonNumber) {
            1 -> resources.getStringArray(R.array.module_1_questions)
            2 ->  resources.getStringArray(R.array.module_2_questions)
            else ->  resources.getStringArray(R.array.module_1_questions)
        }
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

        lessonName.text = getString(R.string.lesson_1_name) + ": " + getString(R.string.lesson_1_title)

        fun loadQuestions(questionNumber : Int, lessonNumber: Int, question: Array<String>) {

            val questionIndex = questionNumber * 6
            lessonName.text = "Module " + lessonNumber.toString()

            questionOutput.text = question[questionIndex]
            choice1.text = question[questionIndex + 1]
            choice2.text = question[questionIndex + 2]
            choice3.text = question[questionIndex + 3]
            choice4.text = question[questionIndex + 4]
            val answer : String = question[questionIndex + 5]
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


        var questionNumber = 0
        loadQuestions(questionNumber, lessonNumber, question)

        continueButton.setOnClickListener {

            if (questionNumber >= ((question.size / 6) - 1)) {

            } else {
                questionNumber += 1
                loadQuestions(questionNumber, lessonNumber, question)
            }

        }
        backButton.setOnClickListener {
            if (questionNumber <= 0) {

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


}