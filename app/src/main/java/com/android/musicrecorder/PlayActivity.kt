package com.android.musicrecorder


import android.app.Dialog
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_play.*
import kotlinx.android.synthetic.main.activity_play.blast
import kotlinx.android.synthetic.main.record_activity.*
import java.io.File


class PlayActivity : AppCompatActivityWithMenu(), Runnable {

    val mediaPlayer = MediaPlayer()
    val CUSTOM_DIALOG_ID: Int = 0
    //    var choice = String()
    lateinit var currFolder: File
    lateinit var fileList: ArrayList<String>
    lateinit var root: File
    val root_sd = Environment.getExternalStorageDirectory().toString()

    companion object {
        var playActivitychoice: String = ""
    }

    override fun run() {
        var currentPosition = 0
        var soundTotal: Int = mediaPlayer.getDuration()
        seekBar.setMax(soundTotal)

        while (mediaPlayer != null && currentPosition < soundTotal) {
            Thread.sleep(300)
            println("playing!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            currentPosition = mediaPlayer.currentPosition
            seekBar.setProgress(currentPosition)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        stopPlayButtonId
        setupListeners()
        fileList = ArrayList<String>()
        fileManager.setOnClickListener {
            showDialog(CUSTOM_DIALOG_ID)
        }
        root = File(Environment.getExternalStorageDirectory().absolutePath)
        currFolder = root
//        val runnable = SimpleRunnable()


//        val serviceAccount = FileInputStream("path/to/serviceAccountKey.json")

//        val options: FirebaseOptions = FirebaseOptions.Builder()
//            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//            .setDatabaseUrl("https://musicrecorder-mk.firebaseio.com")
//            .build()

//        FirebaseApp.initializeApp(options)


    }

    override fun onCreateDialog(id: Int): Dialog? {
        lateinit var dialog: Dialog
//        Dialog(this@PlayActivity)
        when (id) {

            CUSTOM_DIALOG_ID -> {
                mediaPlayer.reset()
                var fileExplorerDialog = FileExplorerDialog()
                fileExplorerDialog.run {
                    show(supportFragmentManager, "example dialog")
                }
                return null

            }
        }
        return dialog
    }


    private fun setupListeners() {
        toggleButtonPlayId.setOnCheckedChangeListener { _, isChecked ->
            run {
                if (isChecked) {
                    Toast.makeText(this@PlayActivity, playActivitychoice, Toast.LENGTH_SHORT).show()
                    if (playActivitychoice != "") {
                        var ch = playActivitychoice
                        var uri = Uri.fromFile(File(playActivitychoice))
                        mediaPlayer.setDataSource(this@PlayActivity, uri)

//                        mediaPlayer.prepare()

                        mediaPlayer.setOnPreparedListener {
                            mediaPlayer.start()
                            blast.animate()
                            val audioSessionId: Int = mediaPlayer.getAudioSessionId()
                            if (audioSessionId != -1) blast.setAudioSessionId(audioSessionId)
                            val thread1 = Thread { run() }
                            thread1.start()

//                                mediaPlayer.audioSessionId = audioSessionId
                        }
                        mediaPlayer.prepareAsync()
                    } else {
                        Toast.makeText(this@PlayActivity, "Load file!", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    mediaPlayer.pause()
                }
            }
        }

        stopPlayButtonId.setOnClickListener {
            mediaPlayer.stop()
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })

    }
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


//}
//
//fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//    myList = ArrayList<String>()
//    val root_sd = Environment.getExternalStorageDirectory().toString()
//    file = File("$root_sd/external_sd")
//    val list: Array<File> = file.listFiles()
//    for (i in list.indices) {
//        myList.add(list[i].name)
//    }
//    setListAdapter(
//        ArrayAdapter<String>(
//            this,
//            android.R.layout.simple_list_item_1, myList
//        )
//    )
//}

