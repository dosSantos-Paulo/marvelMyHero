package com.example.marvelmyhero.db.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.marvelmyhero.db.entity.CardEntity
import com.example.marvelmyhero.db.repository.CardRepository
import kotlinx.coroutines.Dispatchers

class CardViewModel(private val repository: CardRepository): ViewModel() {

    fun addCard(card: CardEntity) = liveData(Dispatchers.IO) {
        repository.addCard(card)
        emit(true)
    }

    class CardViewModelFactory(private val repository: CardRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CardViewModel(repository) as T
        }
    }
}