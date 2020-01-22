package com.android.musicrecorder

import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.record_activity.*
import java.io.File
import java.time.LocalDateTime
import java.time.LocalTime


class RecordActivity : AppCompatActivityWithMenu() {
    lateinit var savePath : File
    val mediaRecorder = MediaRecorder()
    val mediaPlayer = MediaPlayer()
    val REQUEST_PERMISSION_CODE = 1
    var recordingNow = false
    var setRecorder = true

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.record_activity)
        playButtonId.setEnabled(false)
        invalidateOptionsMenu()

        while (!checkPermissionFromDevice()) {
            requestPermission()
        }

        toggleButtonRecordId.setOnCheckedChangeListener { buttonView, isChecked ->
            run {
                if (isChecked) {
                    if (!recordingNow) {
                        try {
                        recordingNow = true
                        if (setRecorder) {
                            var filename =  "/recording" + LocalTime.now().second.toString().replace(".","")+".3gp"
//                            savePath = File(Environment.getExternalStorageDirectory().absolutePath + "/test3.3gp")
                            savePath = File(Environment.getExternalStorageDirectory().absolutePath + filename)
                            createDirIfNotExist(Environment.getExternalStorageDirectory().absolutePath)
                            setupMediaRecorder()
                            setRecorder = false
                            mediaRecorder.start()
//                            mediaRecorder.prepare()
                        } else {
                            mediaRecorder.resume()
                        }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        stopButtonId.setEnabled(true)
                        Toast.makeText(
                            this@RecordActivity,
                            "Recording... to " + savePath,
                            Toast.LENGTH_SHORT
                        )
                            .show()

                    } else {
                        recordingNow = false
                        try {
                            mediaRecorder.pause()
                        } catch (stopException: RuntimeException) { //handle cleanup here
                        }
                    }
                }
            }

        }

        stopButtonId.setOnClickListener {
            recordingNow = false
            toggleButtonRecordId.isChecked = false
            playButtonId.isEnabled = true
//            println(mediaRecorder.toString())
            try {
                mediaRecorder.stop()
            } catch (stopException: RuntimeException) { //handle cleanup here
            }
            mediaRecorder.reset()
            setRecorder = true
            Toast.makeText(
                this@RecordActivity,
                "Saving... to " + savePath,
                Toast.LENGTH_SHORT
            )
                .show()
            stopButtonId.isEnabled = false

        }

        playButtonId.setOnClickListener {
            mediaPlayer.setDataSource(this@RecordActivity, Uri.fromFile(savePath))
            mediaPlayer.setOnPreparedListener {
                mediaPlayer.start()
            }
            mediaPlayer.prepareAsync()

            val audioSessionId: Int = mediaPlayer.getAudioSessionId()
            if (audioSessionId != -1) blast0.setAudioSessionId(audioSessionId)

//                mediaPlayer.setAud
//                mediaPlayer.start()

            Toast.makeText(this@RecordActivity, "Playing...", Toast.LENGTH_SHORT).show()
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupMediaRecorder() {
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_WB)
        mediaRecorder.setOutputFile(savePath)
//        mediaRecorder.setOut
        mediaRecorder.prepare()
//        println(mediaRecorder.outpu)
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this@RecordActivity,
            arrayOf(
                android.Manifest.permission.RECORD_AUDIO,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ), REQUEST_PERMISSION_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_PERMISSION_CODE -> {
                if ((grantResults.size > 0) and (grantResults[0] == PackageManager.PERMISSION_GRANTED))
                    Toast.makeText(
                        this@RecordActivity,
                        "Permission Granted",
                        Toast.LENGTH_SHORT
                    ).show()
                else {
                    Toast.makeText(this@RecordActivity, "Permission Denied", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }


    fun checkPermissionFromDevice(): Boolean {
        val writeExternalStorageResult = ContextCompat.checkSelfPermission(
            this@RecordActivity,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val readExternalStorageResult = ContextCompat.checkSelfPermission(
            this@RecordActivity,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        )
        val recordAudioResult = ContextCompat.checkSelfPermission(
            this@RecordActivity,
            android.Manifest.permission.RECORD_AUDIO
        )
//        val ok = PackageManager.PERMISSION_GRANTED
        return (writeExternalStorageResult == PackageManager.PERMISSION_GRANTED) and
                (readExternalStorageResult == PackageManager.PERMISSION_GRANTED) and
                (recordAudioResult == PackageManager.PERMISSION_GRANTED)
    }

    fun createDirIfNotExist(path: String) {
        var f = File(path)
        var success = true
        if (!f.exists()) {
            success = f.mkdirs()
        }
        if (success) { // Do something on success
        } else { // Do something else on failure
        }
    }
}


//https://www.javahelps.com/2019/04/android-list-external-storage-files.html
//https://www.youtube.com/watch?v=-eFoX4K59qM

//            toggleButtonRecordId.setOnClickListener { view ->
//                Toast.makeText(this@RecordActivity, "recording now", Toast.LENGTH_SHORT).show()
//                if (!recordingNow) {
//                    recordingNow = true
//                    toggleButtonRecordId.isEnabled
//                    savePath =
//                        Environment.getExternalStorageDirectory().absolutePath + "/" + UUID.randomUUID().toString() + "_audio_record.3gp"
//                    try {
//                        mediaRecorder.prepare()
//                        mediaRecorder.start()
//                    } catch (e: Exception) {
//                        e.printStackTrace()
//                    }
//                    stopButtonId.setEnabled(true)
//                    Toast.makeText(this@RecordActivity, "Recording...", Toast.LENGTH_SHORT).show()
//                } else {
//                    recordingNow = false
//                    mediaRecorder.stop()
//                    stopButtonId.setEnabled(false)
//                    playButtonId.setEnabled(true)
//                }
//            }