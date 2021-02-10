package com.rabi.mullu.model

data class Card(
    val type: String?,
    var imageResId: Int = 0,
    var url :String = "",
    var isGif:Boolean = true
)
