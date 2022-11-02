package com.example.codingo

class FillBlank(question : String, answer : String) {
    val question = question
    val answer = answer

    init {
        displayQuestion()
        checkInput()
    }


    private fun displayQuestion() {
        println(question)
    }

    private fun checkInput() {
        val guess = readln()
        if (guess == answer) {
            println("That was correct!")
        }
        else {
            println("That was incorrect!")
        }
    }
}