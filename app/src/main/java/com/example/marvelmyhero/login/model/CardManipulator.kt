package com.example.marvelmyhero.login.model

import com.example.marvelmyhero.card.model.Hero
import com.example.marvelmyhero.database.entity.CardEntity

class CardManipulator {
    private val _cardList = mutableMapOf<Int, Hero>()

    fun add(card: CardEntity) {
        val newCard = Hero(
            card.id, card.heroName, card.name, card.imageUrl,
            card.durability,
            card.energy,
            card.fightingSkills,
            card.inteligence,
            card.speed,
            card.strength,
            card.description
        )

        _cardList[newCard.id] = newCard
    }

    fun getAllCards(): MutableList<Hero> {
        return _cardList.toList().map { it.second }.toMutableList()
    }
}