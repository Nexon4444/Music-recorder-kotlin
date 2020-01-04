package com.android.musicrecorder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {
    lateinit var emailId : EditText
    lateinit var password : EditText
// ...
// Initialize Firebase Auth
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in_activity)

        // ...
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
//        updateUI(currentUser)
}
}
