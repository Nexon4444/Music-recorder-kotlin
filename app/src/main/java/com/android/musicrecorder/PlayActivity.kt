package com.android.musicrecorder

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_play.*
import java.io.File

class PlayActivity : AppCompatActivityWithMenu(), Runnable {
    override fun run() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        stopPlayButtonId
        val mediaPlayer = MediaPlayer()

    }
//
//    private fun listExternalStorage() {
//        val state = Environment.getExternalStorageState()
//
//        if (Environment.MEDIA_MOUNTED == state || Environment.MEDIA_MOUNTED_READ_ONLY == state) {
//            listFiles(Environment.getExternalStorageDirectory())
//            Toast.makeText(this, "Successfully listed all the files!", Toast.LENGTH_SHORT)
//                .show()
//        }
//    }
//
//    /**
//     * Recursively list files from a given directory.
//     */
//    private fun listFiles(directory: File) {
//        val files = directory.listFiles()
//        if (files != null) {
//            for (file in files) {
//                if (file != null) {
//                    if (file.isDirectory) {
//                        listFiles(file)
//                    } else {
//                        txtFiles.append(file.absolutePath + "\n")
//                    }
//                }
//            }
//        }
//    }


}