package com.rabi.mullu.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.rabi.mullu.R
import com.rabi.mullu.model.Card
import com.rabi.mullu.utils.hideStatusBar
import com.rabi.mullu.utils.loadGif
import com.rabi.mullu.utils.visible
import kotlinx.android.synthetic.main.menu_card_layout.*
import kotlinx.coroutines.*

class MenuFragment (private var card: Card,public val action :(String , Any?)->Unit) : Fragment(R.layout.menu_card_layout){

    val scope = CoroutineScope(Job())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideStatusBar()

        birthday_card.setOnClickListener { action("card",null) }
        cake.setOnClickListener { action("cake",null) }
        photo.setOnClickListener { action("photo",null) }
    }

    override fun onStart() {
        super.onStart()
        hideStatusBar()
        rest_image.loadGif(card.imageResId)
        scope.launch(Dispatchers.Main){
            delay(200)
            menu.visible()
        }

    }
}