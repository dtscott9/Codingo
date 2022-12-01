package com.example.codingo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var emailInput : EditText
    private lateinit var passInput : EditText
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        val regButton = findViewById<Button>(R.id.signUp)
        val loginButton = findViewById<Button>(R.id.Login)
        emailInput = findViewById(R.id.emailReg)
        passInput = findViewById(R.id.passwordReg)



        loginButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish();
        }

        regButton.setOnClickListener {
            val emailReg = emailInput.text.toString()
            val passwordReg = passInput.text.toString()
            if (emailReg.isNotEmpty() && passwordReg.isNotEmpty()) {

                auth.createUserWithEmailAndPassword(emailReg, passwordReg).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val login = Intent(this, MainActivity::class.java)
                        startActivity(login)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else {
                Toast.makeText(this, "Fields can't be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

