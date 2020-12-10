package com.example.marvelmyhero.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.marvelmyhero.data.repository.CharacterRepository
import com.example.marvelmyhero.model.CharacterModel
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class CharacterViewModel(
    private val _repository: CharacterRepository
) : ViewModel() {

    var characterList: MutableList<CharacterModel> = mutableListOf()

    fun getCharacter(id: Int) = liveData(Dispatchers.IO) {
        try {
            val result = _repository.getCharacter(id).data.results[0]
            characterList.add(result)
            emit(result)
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