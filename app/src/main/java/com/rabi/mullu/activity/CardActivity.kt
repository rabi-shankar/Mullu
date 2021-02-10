package com.rabi.mullu.activity

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import androidx.appcompat.app.AppCompatActivity
import com.rabi.mullu.R
import com.rabi.mullu.utils.*
import kotlinx.android.synthetic.main.activity_card.*
import kotlinx.coroutines.*
import java.util.*

class CardActivity : AppCompatActivity() {
    val scope = CoroutineScope(Job())
    var lastCard: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        start_rotation.setOnClickListener { goodLuck() }

        try_again.setOnClickListener {
            start_rotation.isClickable = true
            start_rotation.visible()
            msg.visible()
            arrow.visible()
            rest_image.gone()
            try_again.gone(PUSH_RIGHT_OUT)
        }
    }

    private fun goodLuck() {
        val mPosition = getRandomAngle()
        RotateAnimation(
                0f,mPosition,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f
        ).apply{
            duration = getRandomLong(mutableListOf(1440L,1800L,2160L,2520L,2880L,3240L,3600L))
            repeatCount = 0
            fillAfter = true

            setAnimationListener( object : Animation.AnimationListener{
                override fun onAnimationStart(p0: Animation?) {
                    start_rotation.isClickable = false
                }

                override fun onAnimationEnd(p0: Animation?) {
                    start_rotation.isClickable = true
                    scope.launch(Dispatchers.Main) {
                        delay(1000)
                        start_rotation.gone()
                        msg.gone()
                        arrow.gone()
                        getCard()
                    }.start()
                }
                override fun onAnimationRepeat(p0: Animation?) {}
            })

        }.also {
            rotation_view.startAnimation(it)
        }

    }

    private fun getCard() {

        val list = mutableListOf<Int>(
                R.drawable.card_1,
                R.drawable.card_2,
                R.drawable.card_3,
                R.drawable.card_4,
                R.drawable.card_5,
                R.drawable.card_6,
                R.drawable.card_7,
                R.drawable.card_8
        )
        var card = getRandomInt(list)
        if(card == lastCard) card = getRandomInt(list) else lastCard = card
        try {
            if(try_again != null) try_again.visible(PUSH_LEFT_IN)
            if(rest_image!=null) {
                rest_image.visible()
                rest_image.load(card)
            }
        }catch (e: NullPointerException){
            e.stackTrace
        }
    }


    override fun onStart() {
        super.onStart()
        hideStatusBar()
        background.loadGif(R.raw.spnning_background)
    }

    private fun getRandomInt(list: MutableList<Int>): Int {
        list.shuffle(Random())
        return list[0]
    }

    private fun getRandomLong(list: MutableList<Long>): Long {
        list.shuffle(Random())
        return list[0]
    }

    private fun getRandomAngle(): Float {
        val rotation  = mutableListOf(1440,1800,2160,2520,2880)
        val alpha  = mutableListOf(0,45,90,135,180,225,270,315)
        rotation.shuffle(Random())
        alpha.shuffle(Random())
        return (rotation[0] + alpha[0]).toFloat()
    }
}
