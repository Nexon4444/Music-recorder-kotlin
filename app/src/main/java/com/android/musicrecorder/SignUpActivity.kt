package com.android.musicrecorder

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var tvSignIn: EditText
    lateinit var signUpBtn: Button
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_activity)

        // ...
        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance()
        email = findViewById(R.id.editTextEmailId)
        password = findViewById(R.id.editTextPasswdId)
        tvSignIn = findViewById(R.id.noAccountTextViewId)
        signUpBtn = findViewById(R.id.signUpBtnId)
        signUpBtn.setOnClickListener(View.OnClickListener {
            val emailString: String = email.text.toString().trim()
            val passwordString: String = password.text.toString()
            email
            if (email.text.isEmpty()) {
                email.setText("Enter Email!")
                email.requestFocus()
            } else if (password.text.isEmpty()) {
                password.setText("Enter password")
                password.requestFocus()
            } else if (password.text.isEmpty() and email.text.isEmpty()) {
                Toast.makeText(this@SignUpActivity, "Fields are empty!", Toast.LENGTH_SHORT)
            } else if (!password.text.isEmpty() and !email.text.isEmpty()) {
                firebaseAuth
                    .createUserWithEmailAndPassword(emailString, passwordString)
                    .addOnCompleteListener(this@SignUpActivity, OnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                this@SignUpActivity,
                                "Registration succesfull!!",
                                Toast.LENGTH_SHORT
                            )
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                this@SignUpActivity,
                                "Registration unsuccesfull, please try again",
                                Toast.LENGTH_SHORT
                            )

                        }
                    })

            } else {
                Toast.makeText(
                    this@SignUpActivity,
                    "ERROR",
                    Toast.LENGTH_SHORT
                )
            }

        })
        tvSignIn.setOnClickListener { view ->
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

    }
}

//https://youtu.be/V0ZrnL-i77Q?t=1376