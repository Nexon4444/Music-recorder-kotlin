package com.android.musicrecorder

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

import android.os.Bundle

import android.content.res.AssetFileDescriptor
import android.media.MediaPlayer
import android.net.Uri
//import android.support.design.widget.FloatingActionButton;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView

import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_play.*
import kotlinx.android.synthetic.main.activity_play.blast
import kotlinx.android.synthetic.main.activity_play.fileManager
import kotlinx.android.synthetic.main.activity_play2.*
import java.io.File

class PlayActivity2 : AppCompatActivityWithMenu(), Runnable {


    internal var mediaPlayer: MediaPlayer? = MediaPlayer()

    internal lateinit var seekBar: SeekBar
    var wasPlaying = false
    lateinit var fab: ImageButton

    companion object {
        var playActivitychoice: String = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play2)
        val audioSessionId: Int = mediaPlayer!!.getAudioSessionId()
        if (audioSessionId != -1) blast.setAudioSessionId(audioSessionId)
        blast.animate()
        fileManager.setOnClickListener {
            showDialog(0)
        }

        fab = findViewById(R.id.playId2)
        fab.isEnabled = true
        fab.setOnClickListener { playSong() }

        val seekBarHint = findViewById<TextView>(R.id.textView)

        seekBar = findViewById(R.id.seekbar2)
        stopId2.setOnClickListener {
            if (mediaPlayer != null && !mediaPlayer!!.isPlaying) {
                clearMediaPlayer()

                //                    fab.setImageDrawable(ContextCompat.getDrawable(PlayActivity2.this, android.R.drawable.ic_media_play));
                this@PlayActivity2.seekBar.progress = 0
            }
        }
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: SeekBar) {

                seekBarHint.visibility = View.VISIBLE
            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromTouch: Boolean) {
                seekBarHint.visibility = View.VISIBLE
                val x = Math.ceil((progress / 1000f).toDouble()).toInt()

                if (x < 10)
                    seekBarHint.text = "0:0$x"
                else
                    seekBarHint.text = "0:$x"

                val percent = progress / seekBar.max.toDouble()
                val offset = seekBar.thumbOffset
                val seekWidth = seekBar.width
                val `val` = Math.round(percent * (seekWidth - 2 * offset)).toInt()
                val labelWidth = seekBarHint.width
                seekBarHint.x = (offset.toFloat() + seekBar.x + `val`.toFloat()
                        - Math.round(percent * offset).toFloat()
                        - Math.round(percent * labelWidth / 2).toFloat())

                if (progress > 0 && mediaPlayer != null && !mediaPlayer!!.isPlaying) {
                    clearMediaPlayer()

                    //                    fab.setImageDrawable(ContextCompat.getDrawable(PlayActivity2.this, android.R.drawable.ic_media_play));
                    this@PlayActivity2.seekBar.progress = 0
                }

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
                    mediaPlayer!!.seekTo(seekBar.progress)
                }
            }
        })
    }

    override fun onCreateDialog(id: Int): Dialog? {
        lateinit var dialog: Dialog
//        Dialog(this@PlayActivity)

        var fileExplorerDialog = FileExplorerDialog()
        fileExplorerDialog.run {
            show(supportFragmentManager, "example dialog")}
        return null
    }

    fun playSong() {
        try {
            if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
                clearMediaPlayer()
                seekBar.progress = 0
                wasPlaying = true
                //                fab.setImageDrawable(ContextCompat.getDrawable(PlayActivity2.this, android.R.drawable.ic_media_play));
            }


            if (!wasPlaying) {

                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer()
                    val audioSessionId: Int = mediaPlayer!!.getAudioSessionId()
                    if (audioSessionId != -1) blast.setAudioSessionId(audioSessionId)
                    blast.animate()
                }

                //                fab.setImageDrawable(ContextCompat.getDrawable(PlayActivity2.this, android.R.drawable.ic_media_pause));

//                val descriptor = assets.openFd("suits.mp3")
//                mediaPlayer!!.setDataSource(
//                    descriptor.fileDescriptor,
//                    descriptor.startOffset,
//                    descriptor.length
//                )
//                descriptor.close()
                val chice = playActivitychoice
                var check = File(playActivitychoice)
                mediaPlayer!!.setDataSource(this@PlayActivity2,
                    Uri.fromFile(check))
                mediaPlayer!!.prepare()
                mediaPlayer!!.setVolume(0.5f, 0.5f)
                mediaPlayer!!.isLooping = false
                seekBar.max = mediaPlayer!!.duration

                mediaPlayer!!.start()
                Thread(this).start()

            }
            wasPlaying = false
        } catch (e: Exception) {
            e.printStackTrace()

        }

    }

    override fun run() {

        var currentPosition = mediaPlayer!!.currentPosition
        val total = mediaPlayer!!.duration


        while (mediaPlayer != null && mediaPlayer!!.isPlaying && currentPosition < total) {
            try {
                Thread.sleep(1000)
                currentPosition = mediaPlayer!!.currentPosition
            } catch (e: InterruptedException) {
                return
            } catch (e: Exception) {
                return
            }

            seekBar.progress = currentPosition

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        clearMediaPlayer()
    }

    private fun clearMediaPlayer() {
        mediaPlayer!!.stop()
        mediaPlayer!!.release()
        mediaPlayer = null
    }
}