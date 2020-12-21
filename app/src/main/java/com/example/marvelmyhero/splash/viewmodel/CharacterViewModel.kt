package com.example.marvelmyhero.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.marvelmyhero.data.repository.CharacterRepository
import com.example.marvelmyhero.card.model.CharacterModel
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class CharacterViewModel(
    private val _repository: CharacterRepository
) : ViewModel() {

    var characterList: MutableList<CharacterModel> = mutableListOf()

    fun getCharacter(idList: List<Int>) = liveData(Dispatchers.IO) {
        try {
            for (i in idList.indices) {
                val result = _repository.getCharacter(idList[i]).data.results[0]
                characterList.add(result)
            }

            emit(characterList)

        } catch (ex: Exception) {
            println(ex.message)
        }

    }

    class CharacterViewModelFactory(private val _repository: CharacterRepository):ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CharacterViewModel(_repository) as T
        }

    }

}