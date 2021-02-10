package com.rabi.mullu.activity

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.rabi.mullu.R
import com.rabi.mullu.observer.CardEvent
import com.rabi.mullu.observer.MainViewModel
import com.rabi.mullu.utils.hideStatusBar
import kotlinx.coroutines.ExperimentalCoroutinesApi

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    var exitDuration : Long = 0
    val delay  = 3200

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        subscribeCardEventObservers()
        viewModel.registerForEvent(CardEvent.GetCardsEvent("menu"))
    }


    override fun onStart() {
        super.onStart()
        hideStatusBar()
    }

    override fun onBackPressed() {
        onBackPressedKtx {
            super.onBackPressed()
        }
    }


}
