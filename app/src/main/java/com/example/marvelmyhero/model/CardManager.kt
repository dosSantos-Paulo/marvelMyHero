package com.example.marvelmyhero.model

class CardManager {
    private val _cardList = mutableMapOf<Int, Hero>()

    fun addCard(
        id: Int, heroName: String, name: String, imageUrl: String,
        durability: Int,
        energy: Int,
        fightingSkills: Int,
        inteligence: Int,
        speed: Int,
        strenght: Int,
        description: String
    ) {

        val card = Hero(
            id, heroName, name, imageUrl,
            durability,
            energy,
            fightingSkills,
            inteligence,
            speed,
            strenght,
            description
        )

        _cardList[card.id] = card
    }

    fun getAllCards(): MutableList<Hero> {
        return _cardList.toList().map { it.second }.toMutableList()
    }
}