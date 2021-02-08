package com.example.marvelmyhero.login.model

import com.example.marvelmyhero.card.model.Hero

class UserManager {
    private lateinit var _user: User
    private var _isChangeTeam = false

    fun setUser(user: User) {
        _user = user
    }

    fun addOnDeck(list: MutableList<Hero>) {
        _user.deck.addAll(list)
        if (!_isChangeTeam) {
            _user.team = mutableListOf(_user.deck[0], _user.deck[1], _user.deck[2])
        }
    }

    fun setNewTeam(card1: Hero, card2: Hero, card3: Hero) {
        _isChangeTeam = true
        _user.team = mutableListOf(card1, card2, card3)
    }

    fun updateDeck(deck: MutableList<Hero>, team: MutableList<Hero>){
        _user.deck = deck
        _user.team = team
    }

    fun getDeck() = _user.deck
    fun getTeam() = _user.team
}