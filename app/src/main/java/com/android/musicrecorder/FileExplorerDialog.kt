package com.android.musicrecorder

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import java.io.File

class FileExplorerDialog : AppCompatDialogFragment() {
    val CUSTOM_DIALOG_ID: Int = 0
    lateinit var currFolder: File
    var fileList = arrayOf<String>()
    var filePaths = arrayOf<String>()
    var root = File(Environment.getExternalStorageDirectory().toString())
    var choice = String()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder =
            AlertDialog.Builder(activity)

        var dialog: Dialog? = null
        val inflater = activity!!.layoutInflater
        val view = inflater.inflate(R.layout.dialog_layout, null)
        builder.run {
            setView(view)
                .setTitle("File")
                .setNegativeButton("cancel") { dialogInterface, i -> }

        }
        listDir(root)

        builder.setItems(fileList) {dialog, which ->
            choice = filePaths[which]
            PlayActivity2.playActivitychoice = choice
            Toast.makeText(context, "$choice is clicked", Toast.LENGTH_SHORT).show()
        }
        return builder.create()
    }


    fun listDir(f: File) : File?{
        currFolder = f
        var files = f.listFiles()
        for (file: File in files) {
            if (file.extension == "3gp")
            {
                filePaths += file.absolutePath
                fileList += file.name
            }
            else println("not")

        }
        return null

    }
}

