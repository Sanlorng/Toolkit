package com.sanlorng.kit

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * Create by Sanlorng on 2018/4/9
 */

fun Context.exceptionDialog(exception: Exception) {
    val dialog = AlertDialog.Builder(this)
    dialog.setTitle(exception::class.toString())
    dialog.setMessage(exception.message)
    dialog.setCancelable(true)
    dialog.setPositiveButton("OK") {
        _, _ ->
    }
    dialog.show()
}

val Context.defaultSharedPreference:SharedPreferences
get() {
    if (ContextKit.defaultPreference == null)
        ContextKit.defaultPreference = PreferenceManager.getDefaultSharedPreferences(this)
    return ContextKit.defaultPreference!!
}


fun Intent.startBy(context:Context?){
    context?.startActivity(this)
}
fun Context.startActivity(cls:Class<*>){
    startActivity(Intent(this,cls))
}

fun Context.dialog(title:String, content:String,posButton:String,posListener:DialogInterface.OnClickListener){
    val dialog = AlertDialog.Builder(this)
    dialog.setTitle(title)
    dialog.setMessage(content)
    dialog.setCancelable(true)
    dialog.setPositiveButton(posButton,posListener)
    dialog.show()
}

fun Context.dialog(title:String, content:String,posButton:String,posListener:DialogInterface.OnClickListener,negButton:String, negListener:DialogInterface.OnClickListener){
    val dialog = AlertDialog.Builder(this)
    dialog.setTitle(title)
    dialog.setMessage(content)
    dialog.setCancelable(true)
    dialog.setPositiveButton(posButton,posListener)
    dialog.setNegativeButton(negButton,negListener)
    dialog.show()
}

fun Context.processDialog():AlertDialog{
    return AlertDialog.Builder(this)
        .setView(com.sanlorng.kit.R.layout.process_dialog).create()

}
object ContextKit{
    var defaultPreference:SharedPreferences? = null
}