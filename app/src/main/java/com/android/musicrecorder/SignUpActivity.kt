package com.android.musicrecorder

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    lateinit var email : EditText
    lateinit var password : EditText
    lateinit var signUpBtn : Button
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
        signUpBtn.setOnClickListener(View.OnClickListener {

        })
    }
}

