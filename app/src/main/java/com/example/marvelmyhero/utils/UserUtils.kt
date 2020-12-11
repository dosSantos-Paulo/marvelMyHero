package com.example.marvelmyhero.utils

import com.example.marvelmyhero.R
import com.example.marvelmyhero.model.UserManager

class UserUtils {

    fun startListOfUsers() {
        USER_MANAGER.createNewUser("Paulo Rogers", "Paulo Leonardo", "paulo@teste.com", "123456",
            R.drawable.paulo
        )
        USER_MANAGER.createNewUser("Rafa Stark", "Rafael Irineu", "rafael@teste.com", "123456",
            R.drawable.rafa
        )
        USER_MANAGER.createNewUser("GabsWerine", "Gabriel Marrani", "gabriel@teste.com", "123456",
            R.drawable.marrani
        )
        USER_MANAGER.createNewUser("Louk Sky Walker", "Lucas Gabriel", "lgabriel@teste.com", "123456",
            R.drawable.lucas
        )
        USER_MANAGER.createNewUser("Fell Nático", "Felipe Medeiros", "felipe@teste.com", "nota10",
            R.drawable.felipe
        )
    }

    companion object {
        val USER_MANAGER = UserManager()
    }
}