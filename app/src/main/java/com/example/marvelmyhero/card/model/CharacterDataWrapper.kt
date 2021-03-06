package com.example.marvelmyhero.card.model

data class CharacterDataWrapper(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHtml: String,
    val data: CharacterDataContainer,
    val etag: String
)
