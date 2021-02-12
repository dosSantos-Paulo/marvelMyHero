package com.example.marvelmyhero.login.model

import com.example.marvelmyhero.card.model.Hero

data class User(
    var nickName: String,
    var imageUrl: String
) {
//    o team serão as 3 primeiras cartas do deck
    var deck: MutableList<Hero> = mutableListOf()
}