package com.android.musicrecorder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem

open class AppCompatActivityWithMenu : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = MenuInflater(this@AppCompatActivityWithMenu)
        invalidateOptionsMenu()
        inflater.inflate(R.menu.app_menu, menu)

        return true
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {

            R.id.recordId -> {
                val intent = Intent(this, RecordActivity::class.java)
                startActivity(intent)
            }

            R.id.logout -> {
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
            }

            R.id.playId -> {
                val intent = Intent(this, PlayActivity::class.java)
                startActivity(intent)
            }
        }
        return true

    }
}
