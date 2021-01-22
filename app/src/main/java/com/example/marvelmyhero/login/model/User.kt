package com.example.marvelmyhero.login.model

import com.example.marvelmyhero.card.model.Hero

data class User(
    var nickName: String,
    var name: String,
    var imageUrl: String
) {
    var team: MutableList<Hero> = mutableListOf()
    var deck: MutableList<Hero> = mutableListOf()
}