package com.example.marvelmyhero.model

class UserManager {
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
}