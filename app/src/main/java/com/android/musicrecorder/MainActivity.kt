package com.android.musicrecorder

import android.os.Bundle

class MainActivity : AppCompatActivityWithMenu() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }


    inline fun consume(f: () -> Unit): Boolean {
        f()
        return true
    }



}
