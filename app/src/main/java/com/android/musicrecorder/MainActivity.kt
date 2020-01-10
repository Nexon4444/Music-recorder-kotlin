package com.android.musicrecorder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = MenuInflater(this@MainActivity)
        invalidateOptionsMenu()
        inflater.inflate(R.menu.app_menu, menu)

        return true
    }

    inline fun consume(f: () -> Unit): Boolean {
        f()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {

            R.id.recordId -> {
                val intent = Intent(this, RecordActivity::class.java)
                startActivity(intent)
            }
        }
        return true

    }


}
