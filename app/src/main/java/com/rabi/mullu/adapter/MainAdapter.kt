package com.rabi.mullu.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rabi.mullu.fragment.AboutFragment
import com.rabi.mullu.fragment.CakeFragment
import com.rabi.mullu.fragment.MenuFragment
import com.rabi.mullu.fragment.NormalFragment
import com.rabi.mullu.model.Card
import com.rabi.mullu.utils.ABOUT_CARD
import com.rabi.mullu.utils.CAKE_CARD
import com.rabi.mullu.utils.MENU_CARD

class MainAdapter(fragment: FragmentActivity, private val cards: List<Card>,
                  private val action :(String , Any?) ->Unit) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = cards.size
    override fun createFragment(position: Int): Fragment {
        val card = cards[position]
        return when(card.type){
            MENU_CARD-> MenuFragment(card,action)
            CAKE_CARD-> CakeFragment(card,action)
            ABOUT_CARD-> AboutFragment()
            else -> NormalFragment(card)

        }


    }

}