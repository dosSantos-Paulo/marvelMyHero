package com.example.marvelmyhero.utils

import com.example.marvelmyhero.R
import com.example.marvelmyhero.login.model.User

object Constants {
    const val LANGUAGE_PT_BR = "PT-BR"
    const val FAVORITES_PATH = "/favorites"
    const val ID_PATH = "id"
    const val APP_KEY = "APP"
    const val UIID_KEY = "UIID"
    const val EMPTY_STRING = ""
    const val CONTEXT_RESQUEST_CODE = 1
    const val DECK_COLLUMN = 3

    //    Handler Time
    const val HANDLER_TIME: Long = 9000
    const val HANDLER_TIME_ANIMATION: Long = 5000
    const val HANDLER_TIME_ANIMATION_PROGRESS_BAR: Long = 2000
    const val HANDLER_TIME_BRIDGE: Long = 4000
    const val HANDLER_TIME_BRIDGE_2: Long = 2500
    const val HANDLER_1000: Long = 1000

    //    References
    const val NAME = "NAME"
    const val IMAGE = "IMAGE"

    //    Community Variable || CardFront CardBack
    var enterAnim = R.anim.card_flip_left_in
    var exitAnim = R.anim.card_flip_left_out
    var popEnterAnim = R.anim.card_flip_left_in
    var popExitAnim = R.anim.card_flip_left_out
    var isDoubleBackPressed = false
    var isDeckChange = false
    var userNameFromSignup : String = ""

}