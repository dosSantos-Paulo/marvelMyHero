package com.example.marvelmyhero.login.model

import com.example.marvelmyhero.card.model.Hero

data class User(
    var nickName: String,
    val name: String,
    val email: String,
    val password: String,
    var imageUrl: Int
) {
    var team: MutableList<Hero> = mutableListOf()
    var deck: MutableList<Hero> = mutableListOf()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (email != other.email) return false
        if (password != other.password) return false

        return true
    }

    override fun hashCode(): Int {
        var result = email.hashCode()
        result = 31 * result + password.hashCode()
        return result
    }


}