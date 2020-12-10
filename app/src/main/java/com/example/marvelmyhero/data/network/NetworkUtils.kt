package com.example.marvelmyhero.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val MY_PUBLIC_KEY = "93e80c3a3153916ac7c2e41c67c82195"
const val MY_PRIVATE_KEY = "27c1e431978243f0151690d53f0e430f38fb7beb"

class NetworkUtils {

    companion object {
        private const val BASE_URL = "https://gateway.marvel.com"

        fun getRetrofitInstance(baseUrl: String = BASE_URL): Retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}