package com.rabi.mullu.utils

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import com.bumptech.glide.Glide


fun ImageView?.load(resource: Any) {
    Glide.with(this!!.context.applicationContext)
            .load(resource)
            .into(this)
}

fun ImageView?.loadGif(resource: Any) {
    Glide.with(this!!.context.applicationContext)
            .asGif()
            .load(resource)
            .into(this)
}