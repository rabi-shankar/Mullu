package com.rabi.mullu.repo


import com.rabi.mullu.model.Card
import com.rabi.mullu.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepository {

    suspend fun getCards(id:String): Flow<DataState<List<Card>>> = flow {
        emit(DataState.Loading)
        try{
            var data = mutableListOf<Card>()
            when(id){
                "menu"-> data = Data().getMenu()
                "cake"-> data = Data().getCake()
            }
            emit(DataState.Success(data))

        }catch (e: Exception){
            emit(DataState.Error(e))
        }
    }
}