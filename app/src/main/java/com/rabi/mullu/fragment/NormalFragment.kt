package com.rabi.mullu.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.rabi.mullu.R
import com.rabi.mullu.model.Card
import com.rabi.mullu.utils.hideStatusBar
import com.rabi.mullu.utils.load
import com.rabi.mullu.utils.loadGif
import kotlinx.android.synthetic.main.normal_card_layout.*

class NormalFragment (private var card: Card) : Fragment(R.layout.normal_card_layout){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideStatusBar()
        if(card.isGif) rest_image.loadGif(card.imageResId)
        else rest_image.load(card.imageResId)
    }

    override fun onStart() {
        super.onStart()
        hideStatusBar()
    }
}