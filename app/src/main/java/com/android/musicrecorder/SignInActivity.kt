package com.android.musicrecorder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignInActivity : AppCompatActivity() {
    //    lateinit var emailId : EditText
//    lateinit var password : EditText
// ...
// Initialize Firebase Auth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in_activity)

        // ...
        // Initialize Firebase Auth
        lateinit var email: EditText
        lateinit var password: EditText
        lateinit var tvSignUp: TextView
        lateinit var signInBtn: Button
        lateinit var firebaseAuth: FirebaseAuth

        // ...
        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance()
        email = findViewById(R.id.editTextEmailId)
        password = findViewById(R.id.editTextPasswdId)
        val tvSignIn = findViewById<TextView>(R.id.alreadyHaveAccountTextViewId)
        val tv = findViewById<TextView>(R.id.noAccountTextViewId)
        tvSignUp = findViewById(R.id.noAccountTextViewId)
        signInBtn = findViewById(R.id.signInBtnId)

        val authStateListener = FirebaseAuth.AuthStateListener {
            val firebaseUser: FirebaseUser? = firebaseAuth.currentUser
            if (firebaseUser != null) {
                Toast.makeText(this@SignInActivity, "You are logged in", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
            } else {
                Toast.makeText(this@SignInActivity, "Please Login", Toast.LENGTH_SHORT)
            }

            signInBtn.setOnClickListener {
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
                    Toast.makeText(this@SignInActivity
                        , "Fields are empty!", Toast.LENGTH_SHORT)
                } else if (!password.text.isEmpty() and !email.text.isEmpty()) {
                    firebaseAuth
                        .signInWithEmailAndPassword(emailString, passwordString)
                        .addOnCompleteListener(this@SignInActivity, OnCompleteListener {
                        task ->
                            if (task.isSuccessful) {
                                val intToHome = Intent(this@SignInActivity, MainActivity::class.java)
                                startActivity(intToHome)
                            } else {
                                Toast.makeText(
                                    this@SignInActivity,
                                    "Login Unsuccessfull",
                                    Toast.LENGTH_SHORT
                                )
                            }
                    })

                } else {
                    Toast.makeText(
                        this@SignInActivity,
                        "ERROR",
                        Toast.LENGTH_SHORT
                    )
                }

            }
            tvSignUp.setOnClickListener { view ->
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
            }

        }

//    public override fun onStart() {
//        super.onStart()
//        // Check if user is signed in (non-null) and update UI accordingly.
//        val currentUser = auth.currentUser
////        updateUI(currentUser)
    }
}
