package com.rabi.mullu.activity

import android.content.Intent
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

fun MainActivity.subscribeCardEventObservers() {
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

fun MainActivity.setCardAdapter(list : List<Card>){
    if(list.isNotEmpty()){
        viewModel.cards.observe(this, {
            view_pager.adapter = MainAdapter(this,list){ action, _ ->
                when(action){
                    "card" -> startActivity(Intent(this,CardActivity::class.java))
                    "cake" -> startActivity(Intent(this,CakeActivity::class.java))
                    "photo" -> startActivity(Intent(this,PhotoActivity::class.java))
                }

            }
        })
    }
}


fun MainActivity.onBackPressedKtx(action:()->Unit){
    if (exitDuration + delay > System.currentTimeMillis()) action.invoke()
    else getString(R.string.exit_msg).toast(this)
    exitDuration = System.currentTimeMillis()
}

