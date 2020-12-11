package com.example.marvelmyhero.model

data class User (
    var nickName: String,
    val name: String,
    val email: String,
    val password: String,
    var imageUrl: Int
){
    var team: MutableList<Hero> = mutableListOf()
    var deck: MutableList<Hero> = mutableListOf()
    val stringId = email + password
}