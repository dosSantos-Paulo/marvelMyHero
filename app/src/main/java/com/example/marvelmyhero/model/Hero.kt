package com.example.marvelmyhero.model

data class Hero (
    val id: Int,
    val heroName: String,
    val name: String,
    val imageUrl: Int,

    val durability: Int,
    val energy: Int,
    val fightingSkills: Int,
    val inteligence: Int,
    val speed: Int,
    val strength: Int,

    val history: String,
){
    val averageDivider = 6.0
    val classification: Double = (durability + energy + fightingSkills + inteligence + speed + strength) / averageDivider
}