package com.example.marvelmyhero.model

import com.example.marvelmyhero.utils.UserUtils

class AllUsersManager {
    private val _listOfUsers = mutableListOf<User>()

    fun createNewUser(
        nickName: String,
        name: String,
        email: String,
        password: String,
        imageUrl: Int
    ) {
        val newUser = User(nickName, name, email, password, imageUrl)

        _listOfUsers.add(newUser)
    }

    fun getUser(user: User): User? {
        _listOfUsers.forEach {
            if (user == it){
                return it
            }
        }
        return null
    }

    fun login(email: String?, password: String?): User? {
        var testUser: User?
        if (email == null || password == null) {
            testUser = null
        } else {
            testUser = User("", "", email, password, 0)
        }



        return testUser?.let { getUser(it) }
    }
}