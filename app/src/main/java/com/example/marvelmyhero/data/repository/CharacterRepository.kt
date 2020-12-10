package com.example.marvelmyhero.data.repository

import com.example.marvelmyhero.data.network.MY_PUBLIC_KEY
import com.example.marvelmyhero.data.network.getHash
import com.example.marvelmyhero.data.network.getTimeStamp

class CharacterRepository {

    private val _client = CharacterEndpoint.Endpoint

    suspend fun getCharacter(id:Int) = _client.getCharacter(
            id,
            getTimeStamp(),
            getHash(),
            MY_PUBLIC_KEY
    )
}