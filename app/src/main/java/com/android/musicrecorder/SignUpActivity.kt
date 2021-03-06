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
import kotlinx.android.synthetic.main.sign_in_activity.*
import kotlinx.android.synthetic.main.sign_up_activity.*

class SignUpActivity : AppCompatActivity() {
    lateinit var email: EditText
    lateinit var password: EditText
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

        signUpBtn = findViewById(R.id.signUpBtnId)
        alreadyHaveAccount.setOnClickListener { view ->
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        signUpBtn.setOnClickListener {
            val emailString: String = email.text.toString().trim()
            val passwordString: String = password.text.toString()
            if (email.text.isEmpty()) {
                email.setText("Enter Email!")
                email.requestFocus()
            } else if (password.text.isEmpty()) {
                password.setText("Enter password")
                password.requestFocus()
            } else if (password.text.isEmpty() and email.text.isEmpty()) {
                Toast.makeText(this@SignUpActivity, "Fields are empty!", Toast.LENGTH_SHORT).show()
            } else if (!password.text.isEmpty() and !email.text.isEmpty()) {
                firebaseAuth
                    .createUserWithEmailAndPassword(emailString, passwordString)
                    .addOnCompleteListener(this@SignUpActivity) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                this@SignUpActivity,
                                "Registration succesfull!!",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                this@SignUpActivity,
                                "Registration unsuccesfull, please try again",
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                    }

            } else {
                Toast.makeText(
                    this@SignUpActivity,
                    "ERROR",
                    Toast.LENGTH_SHORT
                ).show()
            }


        }

    }
}

//https://youtu.be/V0ZrnL-i77Q?t=1376