package com.example.marvelmyhero.utils

import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.marvelmyhero.data.repository.CharacterRepository
import com.example.marvelmyhero.model.CardManager
import com.example.marvelmyhero.model.CharacterModel
import com.example.marvelmyhero.viewmodel.CharacterViewModel

class CardUtils () {

    fun addCardOnManager(list: MutableList<CharacterModel>) {

        CARD_MANAGER

        for (i in list.indices) {
            when (list[i].id) {

                THANOS -> {
                    CARD_MANAGER.addCard(
                        list[i].id,
                        list[i].name,
                        "Thanos",
                        "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                        6,
                        6,
                        4,
                        6,
                        7,
                        7,
                        list[i].description
                    )
                }

                STRANGE -> {
                    CARD_MANAGER.addCard(
                        list[i].id,
                        list[i].name,
                        "Stephen Strange",
                        "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                        3,
                        6,
                        3,
                        4,
                        7,
                        2,
                        list[i].description
                    )
                }

                CAPTAIN -> {
                    CARD_MANAGER.addCard(
                        list[i].id,
                        list[i].name,
                        "Steve Rogers",
                        "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                        3,
                        1,
                        6,
                        3,
                        2,
                        3,
                        list[i].description
                    )
                }

                NICK_FURY -> {
                    CARD_MANAGER.addCard(
                        list[i].id,
                        list[i].name,
                        "Nicholas Joseph Fury Jr.",
                        "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                        2,
                        1,
                        6,
                        3,
                        2,
                        2,
                        list[i].description
                    )
                }

                IRON_MAN -> {
                    CARD_MANAGER.addCard(
                        list[i].id,
                        list[i].name,
                        "Tony Stark",
                        "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                        6,
                        6,
                        4,
                        6,
                        5,
                        6,
                        list[i].description
                    )
                }

                BLACK_PANTHER -> {
                    CARD_MANAGER.addCard(
                        list[i].id,
                        list[i].name,
                        "T'Challa",
                        "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                        3,
                        3,
                        5,
                        5,
                        2,
                        3,
                        list[i].description
                    )
                }

                BLACK_WIDOW -> {
                    CARD_MANAGER.addCard(
                        list[i].id,
                        list[i].name,
                        "Natasha Romanoff",
                        "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                        2,
                        1,
                        6,
                        3,
                        3,
                        2,
                        list[i].description
                    )
                }

                SPIDER_MAN -> {
                    CARD_MANAGER.addCard(
                        list[i].id,
                        list[i].name,
                        "Peter Parker",
                        "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                        3,
                        1,
                        4,
                        4,
                        3,
                        4,
                        list[i].description
                    )
                }

                THOR -> {
                    CARD_MANAGER.addCard(
                        list[i].id,
                        list[i].name,
                        "Thor Odinson",
                        "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                        6,
                        6,
                        4,
                        2,
                        7,
                        7,
                        list[i].description
                    )
                }

                LOKI -> {
                    CARD_MANAGER.addCard(
                        list[i].id,
                        list[i].name,
                        "Loki Laufeyson",
                        "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                        6,
                        6,
                        3,
                        5,
                        7,
                        5,
                        list[i].description
                    )
                }

            }
        }

    }



    companion object {
        val CARD_MANAGER = CardManager()

        const val THANOS = 1009652
        const val STRANGE = 1017300
        const val CAPTAIN = 1009220
        const val NICK_FURY = 1011007
        const val IRON_MAN = 1009368
        const val BLACK_PANTHER = 1009187
        const val BLACK_WIDOW = 1017109
        const val SPIDER_MAN = 1009610
        const val THOR = 1009664
        const val LOKI = 1009407
    }
}