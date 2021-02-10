package com.rabi.mullu.activity

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.rabi.mullu.R
import com.rabi.mullu.observer.CardEvent
import com.rabi.mullu.observer.MainViewModel
import com.rabi.mullu.utils.hideStatusBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

class CakeActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var player: MediaPlayer
    var activePlayer = false

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        subscribeCardEventObservers()
        viewModel.registerForEvent(CardEvent.GetCardsEvent("cake"))
        mute_song.setOnClickListener { stopSong() }
    }

    override fun onStop() {
        super.onStop()
        stopSong()
    }

    override fun onStart() {
        super.onStart()
        hideStatusBar()
    }
}