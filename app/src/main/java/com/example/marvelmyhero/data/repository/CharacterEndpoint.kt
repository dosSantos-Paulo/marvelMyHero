package com.example.marvelmyhero.data.repository

import com.example.marvelmyhero.data.network.NetworkUtils
import com.example.marvelmyhero.card.model.CharacterDataWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterEndpoint {

    @GET("/v1/public/characters")
    suspend fun getCharacter(
            @Query("id") id: Int,
            @Query("ts") ts: String?,
            @Query("hash") hash: String?,
            @Query("apikey") apikey: String?
    ): CharacterDataWrapper

    companion object {
        val Endpoint: CharacterEndpoint by lazy {
            NetworkUtils.getRetrofitInstance().create(CharacterEndpoint::class.java)
        }
    }
}