package com.example.marvelmyhero.db.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.marvelmyhero.db.entity.CardEntity
import com.example.marvelmyhero.db.repository.CardRepository
import kotlinx.coroutines.Dispatchers

class CardViewModel(private val repository: CardRepository) : ViewModel() {

    fun addCard(card: CardEntity) = liveData(Dispatchers.IO) {
        try {
            repository.addCard(card)
            emit(true)
        } catch (ex: Exception) {
            println("Error when inserting object from database")
            println("erro: ${ex.message}")
            Log.e("DB_ERROR:", ex.message.toString())
            emit(false)
        }

    }

    fun count() = liveData(Dispatchers.IO) {
        try {
            val count = repository.count()
            emit(count.toInt())
        } catch (ex: Exception) {
            println("Error when count object from database")
            println("erro: ${ex.message}")
            Log.e("DB_ERROR:", ex.message.toString())
            emit(false)
        }
    }

    fun getCard(id: Int) = liveData(Dispatchers.IO) {
        try {
            val card = repository.getCard(id)
            emit(card)
        } catch (ex: Exception) {
            println("Error when getting object from database")
            println("erro: ${ex.message}")
            Log.e("DB_ERROR:", ex.message.toString())
            emit(false)
        }
    }

    fun getAllCards() = liveData(Dispatchers.IO) {
        try {
            val cardList = repository.getAllCards()
            emit(cardList)
        } catch (ex: Exception) {
            println("Error when getting object from database")
            println("erro: ${ex.message}")
            Log.e("DB_ERROR:", ex.message.toString())
            emit(false)
        }
    }

    class CardViewModelFactory(private val repository: CardRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CardViewModel(repository) as T
        }

    }
}