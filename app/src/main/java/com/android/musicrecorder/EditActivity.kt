package com.android.musicrecorder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class EditActivity : AppCompatActivityWithMenu() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
    }
}
