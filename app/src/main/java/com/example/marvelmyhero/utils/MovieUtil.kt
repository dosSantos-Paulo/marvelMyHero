package com.example.marvelmyhero.utils

import android.content.Context
import android.util.Patterns
import com.example.marvelmyhero.utils.Constants.APP_KEY
import com.example.marvelmyhero.utils.Constants.EMPTY_STRING
import com.example.marvelmyhero.utils.Constants.UIID_KEY

object MovieUtil {
    fun saveUserId(context: Context, uiid: String?) {
        val preferences = context.getSharedPreferences(APP_KEY, Context.MODE_PRIVATE)
        preferences.edit().putString(UIID_KEY, uiid).apply()
    }

    fun getUserId(context: Context): String? {
        val preferences = context.getSharedPreferences(APP_KEY, Context.MODE_PRIVATE)
        return preferences.getString(UIID_KEY, EMPTY_STRING)
    }

    fun validateEmailPasswordLogin(email: String, password: String): Boolean {

        return when {
            email.isEmpty() || password.isEmpty() -> {
                false
            }
            Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                false
            }
            password.length < 6 -> {
                false
            }
            else -> true
        }
    }

    fun validateEmailPassword(email: String, password: String): Boolean {

        return when {
            email.isEmpty() || password.isEmpty() -> {
                false
            }
            Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                false
            }
            password.length < 6 -> {
                false
            }
            else -> true
        }
    }
}