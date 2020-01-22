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
    var root = File(Environment.getExternalStorageDirectory().toString())
    var choice = String()
//    val root_sd = Environment.getExternalStorageDirectory().toString() +"/Music"



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

//                .setItems(fileList,
//                DialogInterface.OnClickListener { dialog, which ->
//                    choice = fileList[which]
//                    //you can do stuff with the file here too
//                })


//                })
//        builder.setItems(mFileList,
//                DialogInterface.OnClickListener { dialog, which ->
//                    mChosenFile = mFileList!![which]
//                    //you can do stuff with the file here too
//                })
        }
//        var listener = DialogInterface.OnClickListener
        listDir(root)
        val onClickListener = DialogInterface.OnClickListener{ dialogInterface: DialogInterface, position: Int ->
            run {
                listDir(File(fileList[position]))
            }
        }

        builder.setItems(fileList) {dialog, which ->
//            if (File(fileList[which]).isDirectory)
//            {
//                builder.show()
//            }
            choice = fileList[which]
            PlayActivity.playActivitychoice= choice as String
            Toast.makeText(context, "$choice is clicked", Toast.LENGTH_SHORT).show()
        }
//        builder.setOnItemSelectedListener(dialog, which ->
//        )
//        val listener = AdapterView.OnItemClickListener { adapterView: AdapterView<*>, view1: View, position : Int, l: Long ->
//            adapterView.onItemClickListener = (
//            println("asdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasd")
//        }
//        builder.setOnItemSelectedListener(listener)
        return builder.create()
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        upButton.setOnClickListener {
//            ListDir(currFolder.parentFile)
//            println("clicked!!")
//            Toast.makeText(
//                context,
//                "button clicked",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//    }

    fun listDir(f: File) : File?{
//        upButton.isEnabled = !f.equals(root)
//        if (f.extension == ".3gp") return f
        currFolder = f
//        folder.text = f.path
        var files = f.listFiles()
        for (file: File in files) {
            if (file.extension == "3gp") fileList += file.name
            else println("not")

        }
//        var directoryList = ArrayAdapter<String>(this@FileExplorerDialog, android.R.layout.simple_list_item_1, fileList)
//        dialogList.adapter = directoryList
        return null

    }
}

