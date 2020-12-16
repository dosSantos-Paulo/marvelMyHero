package com.example.marvelmyhero.card.model

data class CharacterDataContainer(
    val results: List<CharacterModel>,
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int
)
