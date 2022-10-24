package com.example.codingo


class MultipleChoice(question : String, choices : List<String>, answer : String) {
    val question = question
    val choices = choices
    val answer = answer

    init {
        displayQuestion()
        checkInput()
    }


    private fun displayQuestion() {
        println(question)
        for (choice in choices) {
            println(choice)
        }
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


fun main() {
    val question = "What is my name?"
    val choices = listOf<String>("Seth", "Kaleb", "Gonzalo", "Dylan")
    val answer = "A"
    MultipleChoice(question, choices, answer)
}