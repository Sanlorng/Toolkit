package com.sanlorng.kit

import android.annotation.TargetApi
import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager

fun Window.statusBarTransucent(){
    this.statusBarColor = this.context.getColor(R.color.statusBarColor)
    this.fitSystemLayout()
}

fun Window.statusBarLight(light: Boolean){
    var ui = this.decorView.systemUiVisibility
    if (light)
        ui = ui or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    else
        ui = ui and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
    this.decorView.systemUiVisibility = ui
}

fun Window.navigationBarTransucent(){
    this.navigationBarColor = this.context.getColor(R.color.navigationColor)
    this.fitSystemLayout()
}

fun Window.navigationBarLight(light: Boolean){
    var ui = this.decorView.systemUiVisibility
    if (light)
        if (Build.VERSION.SDK_INT >= 26)
            ui = ui or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
    else
            if (Build.VERSION.SDK_INT >= 26)
                ui = ui and View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR.inv()
    this.decorView.systemUiVisibility = ui
}

fun Window.systemLight(light: Boolean){
    var ui = this.decorView.systemUiVisibility
    if (light) {
        ui = ui or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        if (Build.VERSION.SDK_INT >= 26)
            ui = ui or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
    }
    else {
        ui = ui and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        if (Build.VERSION.SDK_INT >= 26)
            ui = ui and View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR.inv()
    }
    this.decorView.systemUiVisibility = ui
}

fun Window.fitSystemLayout(){
    var ui = this.decorView.systemUiVisibility
    ui = ui or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    this.decorView.systemUiVisibility = ui
    this.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

}
fun Window.transucentSystemUI(){
    this.transucentSystemUI(false)
}
fun Window.transucentSystemUI(light: Boolean){
    this.statusBarColor = this.context.getColor(R.color.statusBarColor)
    this.navigationBarColor = this.context.getColor(R.color.navigationColor)
    this.systemLight(light)
    this.fitSystemLayout()
}
fun Window.systemLowProfile(hide: Boolean){
    var ui = this.decorView.systemUiVisibility
    if (hide)
        ui = ui or View.SYSTEM_UI_FLAG_LOW_PROFILE
    else
        ui = ui and View.SYSTEM_UI_FLAG_LOW_PROFILE.inv()
    this.decorView.systemUiVisibility = ui
}

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
fun Window.setFullTruncentStatusBar(){
    this.statusBarColor = this.context.getColor(R.color.navigationColor)
}