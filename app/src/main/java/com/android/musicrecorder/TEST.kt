//import android.app.AlertDialog
//import android.app.Dialog
//import android.content.DialogInterface
//
//
//private var mFileList: Array<String?>?
//private val mPath: File = File(Environment.getExternalStorageDirectory().toString() + "//yourdir//")
//private var mChosenFile: String? = null
//private const val FTYPE = ".txt"
//private const val DIALOG_LOAD_FILE = 1000
//
//private open fun loadFileList() {
//    try {
//        mPath.mkdirs()
//    } catch (e: SecurityException) {
//        Log.e(TAG, "unable to write on the sd card $e")
//    }
//    mFileList = if (mPath.exists()) {
//        val filter: FilenameFilter = object : FilenameFilter() {
//            fun accept(dir: File?, filename: String): Boolean {
//                val sel = File(dir, filename)
//                return filename.contains(FTYPE) || sel.isDirectory()
//            }
//        }
//        mPath.list(filter)
//    } else {
//        arrayOfNulls(0)
//    }
//}
//
//fun onCreateDialog(id: Int): Dialog? {
//    var dialog: Dialog? = null
//    val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
//    when (id) {
//        DIALOG_LOAD_FILE -> {
//            builder.setTitle("Choose your file")
//            if (mFileList == null) {
////                Log.e(TAG, "Showing file picker before loading the file list")
//                dialog = builder.create()
//                return dialog
//            }
//            builder.setItems(mFileList,
//                DialogInterface.OnClickListener { dialog, which ->
//                    mChosenFile = mFileList!![which]
//                    //you can do stuff with the file here too
//                })
//        }
//    }
//    dialog = builder.show()
//    return dialog
//}