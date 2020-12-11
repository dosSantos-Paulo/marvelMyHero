package com.example.marvelmyhero.model

class UserManager {
    private val _listOfUsers = mutableMapOf<String, User>()

    fun createNewUser(
        nickName: String,
        name: String,
        email: String,
        password: String,
        imageUrl: Int
    ) {
        val newUser = User(nickName, name, email, password, imageUrl)

        _listOfUsers[newUser.stringId] = newUser
    }

    fun getUserById(id: String): User? {
        return _listOfUsers[id]
    }
}