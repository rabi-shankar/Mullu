package com.rabi.mullu.fragment

import android.content.Context
import android.graphics.Color
import android.os.PowerManager
import android.util.Log
import com.rabi.mullu.utils.*
import kotlinx.android.synthetic.main.cake_card_layout.*
import kotlinx.android.synthetic.main.cake_card_layout.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size

fun CakeFragment.initAudioSensor(){
    mSensor = AudioSensor()
    val powerManager = context?.getSystemService(Context.POWER_SERVICE) as PowerManager
    mWakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "CakeFragment:NoiseAlert")
}


fun CakeFragment.startSensor(){
    isSensorActive = true
    scope.launch(Dispatchers.Main){
        mSensor.start()
        if (!mWakeLock.isHeld) {
            mWakeLock.acquire(10*60*1000L /*10 minutes*/)
        }
        while (isSensorActive){
            if (mSensor.getAmplitude() > mThreshold) initVisual()
            delay(300L)
        }
    }.start()
}

fun CakeFragment.stopSensor(){
    if (mWakeLock.isHeld) mWakeLock.release()
    mSensor.stop()
    isSensorActive = false
}

suspend  fun CakeFragment.initVisual() {
    if (actionDuration + 400 > System.currentTimeMillis()) {
        stopSensor()
        action("start_song",null)
        on_cake_image.gone(FADE_OUT)
        on_to_off_cake_image.visible(FADE_IN)
        streamFromTop()
        delay(3600)
        try{
            if(on_to_off_cake_image != null) on_to_off_cake_image.gone(FADE_OUT)
            if(off_cake_image != null) off_cake_image.visible(FADE_IN)

        }catch (e: KotlinNullPointerException){
            Log.e("TAB","${e.stackTrace}")
        }
    }
    actionDuration = System.currentTimeMillis()
}



fun CakeFragment.streamFromTop() {
    viewKonfetti.build()
        .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
        .setDirection(0.0, 359.0)
        .setSpeed(1f, 5f)
        .setFadeOutEnabled(true)
        .setTimeToLive(2500L)
        .addShapes(Shape.Square, Shape.Circle)
        .addSizes(Size(12))
        .setPosition(-50f, view!!.viewKonfetti.width + 50f, -50f, -50f)
        .streamFor(150, 4000L)
}