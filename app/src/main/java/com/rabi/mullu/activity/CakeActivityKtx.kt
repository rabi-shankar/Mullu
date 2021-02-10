package com.rabi.mullu.activity

import android.media.MediaPlayer
import androidx.viewpager2.widget.ViewPager2
import com.rabi.mullu.R
import com.rabi.mullu.adapter.MainAdapter
import com.rabi.mullu.model.Card
import com.rabi.mullu.utils.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun CakeActivity.subscribeCardEventObservers() {
    view_pager.setPageTransformer(DefaultTransformer())
    view_pager.orientation = ViewPager2.ORIENTATION_VERTICAL
    viewModel.cards.observe(this, {
        when(it){
            is DataState.Success<List<Card>> -> setCardAdapter(it.data)
            is DataState.Error -> {}
            is DataState.Loading ->{}
        }
    })
}

fun CakeActivity.setCardAdapter(list : List<Card>){
    if(list.isNotEmpty()){
        viewModel.cards.observe(this, {
            view_pager.adapter = MainAdapter(this,list){ action, _ ->
                when(action){
                    "start_song" -> startSong()
                }

            }
        })
    }
}


fun CakeActivity.startSong() {

    GlobalScope.launch(Dispatchers.Main){
        mute_song.visible(PUSH_LEFT_IN)
        player = MediaPlayer.create(this@startSong, R.raw.happy_bday_many_people_voice)
        player.isLooping = false // Set looping
        player.setVolume(100F,100F)
        if(player.isPlaying) player.stop()
        player.start()
        activePlayer = player.isPlaying
        view_pager.isUserInputEnabled = true
        player.setOnCompletionListener {
            stopSong()
        }
    }.start()

}

fun CakeActivity.stopSong() {
    if(activePlayer){
        mute_song.invisible(PUSH_RIGHT_OUT)
        activePlayer = false
        player.stop()
        player.release()
    }
}
