package com.android.musicrecorder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater

class RecordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.record_activity)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = MenuInflater(this@RecordActivity)
        invalidateOptionsMenu()
        inflater.inflate(R.menu.app_menu, menu)

        return true
    }
}
//https://www.youtube.com/watch?v=-eFoX4K59qM