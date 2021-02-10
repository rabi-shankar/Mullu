package com.rabi.mullu.fragment

import android.os.Bundle
import android.os.PowerManager
import android.view.View
import androidx.fragment.app.Fragment
import com.rabi.mullu.R
import com.rabi.mullu.activity.MainActivity
import com.rabi.mullu.model.Card
import com.rabi.mullu.utils.*
import kotlinx.android.synthetic.main.cake_card_layout.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job


class CakeFragment (var card: Card, val action :(String, Any?) -> Unit) : Fragment (R.layout.cake_card_layout){

    var actionDuration: Long = 0
    var isSensorActive = false

    //config state
    var mThreshold = 6
    lateinit var mWakeLock: PowerManager.WakeLock
    lateinit var mSensor : AudioSensor
    val scope = CoroutineScope(Job())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideStatusBar()
        initAudioSensor()
        on_cake_image.loadGif(R.raw.on_cake_candle_gif_m)
        on_to_off_cake_image.loadGif(R.raw.on_to_off_cake_gif_m)
        off_cake_image.load(card.imageResId)
    }

    override fun onStart() {
        super.onStart()
        on_cake_image.visible()
        on_to_off_cake_image.gone()
        off_cake_image.gone()
        hideStatusBar()

    }
    override fun onResume() {
        super.onResume()
        if (!isSensorActive) startSensor()

    }


    override fun onStop() {
        super.onStop()
        stopSensor()
    }


}