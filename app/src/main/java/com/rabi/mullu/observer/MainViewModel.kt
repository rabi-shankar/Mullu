package com.rabi.mullu.observer


import android.webkit.WebView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rabi.mullu.model.Card
import com.rabi.mullu.repo.MainRepository
import com.rabi.mullu.utils.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel : ViewModel(){

    private val repo = MainRepository()

    private val _cards: MutableLiveData<DataState<List<Card>>> = MutableLiveData()
    val cards: LiveData<DataState<List<Card>>>
        get() = _cards


    @ExperimentalCoroutinesApi
    fun registerForEvent(stateEvent: CardEvent){
        viewModelScope.launch {
            when(stateEvent){
               is CardEvent.GetCardsEvent -> {
                    repo.getCards(stateEvent.id).onEach {
                        _cards.value = it
                    }.launchIn(viewModelScope)
                }

                CardEvent.None -> {
                    // who cares
                }
            }
        }
    }

}

sealed class CardEvent{
    class GetCardsEvent(val id :String): CardEvent()
    object None: CardEvent()
}
