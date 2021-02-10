package com.rabi.mullu.repo

import com.rabi.mullu.R
import com.rabi.mullu.model.Card
import com.rabi.mullu.utils.ABOUT_CARD
import com.rabi.mullu.utils.CAKE_CARD
import com.rabi.mullu.utils.MENU_CARD
import com.rabi.mullu.utils.NORMAL_CARD


class Data {

    fun getMenu() : MutableList<Card>{
        val cards  = mutableListOf<Card>()

        Card(type = NORMAL_CARD).apply{
            imageResId = R.raw.welcome_mullu
        }.also{ cards.add(it) }

        Card(type = MENU_CARD).apply{
            imageResId = R.raw.wish_mullu
        }.also{ cards.add(it) }

        Card(type = ABOUT_CARD).apply{
            imageResId = 0
        }.also{ cards.add(it) }

        return cards
    }

    fun getCake(): MutableList<Card> {

        val cards  = mutableListOf<Card>()

        Card(type = NORMAL_CARD).apply{
            imageResId = R.raw.blow_air
        }.also{ cards.add(it) }


        Card(type = CAKE_CARD).apply{
            imageResId = R.drawable.off_cake_candle_m
            isGif = false
        }.also{ cards.add(it) }

        return cards

    }
}