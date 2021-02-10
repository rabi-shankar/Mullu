package com.rabi.mullu.utils

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * Hide the status bar.
 */
fun AppCompatActivity.hideStatusBar(){
    this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
}

fun AppCompatActivity.showStatusBar(){
    this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_IMMERSIVE
}


/**
 * Hide the status bar.
 */
fun Fragment.hideStatusBar(){
    (requireActivity() as AppCompatActivity).hideStatusBar()
}

fun Fragment.showStatusBar(){
    (requireActivity() as AppCompatActivity).showStatusBar()
}
