package com.sanlorng.kit

import android.annotation.TargetApi
import android.os.Build
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager

fun Window.setFullTruncentStatusBar(){
    this.statusBarColor = this.context.getColor(R.color.navigationColor)
}
fun Window.statusBarTransucent(){
    this.statusBarColor = this.context.getColor(R.color.statusBarColor)
    this.fitSystemLayout()
}

fun Window.statusBarLight(light: Boolean){
    var ui = this.decorView.systemUiVisibility
    ui = if (light)
        ui or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    else
        ui and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
    this.decorView.systemUiVisibility = ui
}

fun Window.navigationBarTransucent(){
    this.navigationBarColor = this.context.getColor(R.color.navigationColor)
    this.fitSystemLayout()
}
@TargetApi(26)
fun Window.navigationBarLight(light: Boolean){
    var ui = this.decorView.systemUiVisibility
    if (light)
        if (Build.VERSION.SDK_INT >= 26)
            ui = ui or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        else
            if (Build.VERSION.SDK_INT < 26)
                ui = ui and View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR.inv()
    this.decorView.systemUiVisibility = ui
}

fun Window.systemLight(light: Boolean){
    statusBarLight(light)
    navigationBarLight(light)
}

fun Window.fitSystemLayout(){
    var ui = this.decorView.systemUiVisibility
    ui = ui or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    this.decorView.systemUiVisibility = ui
    this.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

}
fun Window.translucentSystemUI(){
    this.translucentSystemUI(false)
}
fun Window.translucentSystemUI(light: Boolean){
    val enable = PreferenceManager.getDefaultSharedPreferences(context).getBoolean("status_bar_mask",false)
    this.statusBarColor = if (enable)
        context.getColor(R.color.statusBarColor)
    else
        context.getColor(R.color.zeroColor)
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
fun Window.openStatusBarShadow(enable:Boolean){
    val isOpen = PreferenceManager.getDefaultSharedPreferences(context).getBoolean("status_bar_mask",false)
    Log.d("window pacakge",context.packageName)
    if (isOpen != enable) {
        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putBoolean("status_bar_mask", enable)
                .apply()
        statusBarColor = if (enable)
            context.getColor(R.color.statusBarColor)
        else
            context.getColor(R.color.zeroColor)
    }
}
fun Window.setStatusBarShadow(enable: Boolean){
        statusBarColor = if (enable)
            context.getColor(R.color.statusBarColor)
        else
            context.getColor(R.color.zeroColor)
}