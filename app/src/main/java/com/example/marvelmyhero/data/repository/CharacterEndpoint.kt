package com.example.marvelmyhero.data.repository

import com.example.marvelmyhero.data.network.NetworkUtils
import com.example.marvelmyhero.model.CharacterDataWrapper
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterEndpoint {

    @GET("/v1/public/characters")
    suspend fun getCharacter(
            @Path("id") id: Int,
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