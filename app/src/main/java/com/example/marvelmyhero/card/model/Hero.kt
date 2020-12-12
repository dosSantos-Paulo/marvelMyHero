package com.example.marvelmyhero.card.model

data class Hero(
    val id: Int,
    val heroName: String,
    val name: String,
    val imageUrl: String,

    val durability: Int,
    val energy: Int,
    val fightingSkills: Int,
    val inteligence: Int,
    val speed: Int,
    val strength: Int,

    val description: String,
) {
    var favorite = false
    val averageDivider = 6.0
    val classification: Double =
        (durability + energy + fightingSkills + inteligence + speed + strength) / averageDivider
}