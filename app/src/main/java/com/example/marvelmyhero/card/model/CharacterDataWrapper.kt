package com.example.marvelmyhero.card.model

import com.example.marvelmyhero.card.model.CharacterDataContainer

data class CharacterDataWrapper(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHtml: String,
    val data: CharacterDataContainer,
    val etag: String
)
