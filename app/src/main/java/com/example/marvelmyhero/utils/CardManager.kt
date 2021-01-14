package com.example.marvelmyhero.utils

import com.example.marvelmyhero.card.model.CharacterModel
import com.example.marvelmyhero.database.entity.CardEntity

class CardManager {

    private var cardManager = mutableListOf<CardEntity>()

    private var listOfIds = listOf<Int>(
        THANOS,
        STRANGE,
        CAPTAIN,
        NICK_FURY,
        IRON_MAN,
        BLACK_PANTHER,
        BLACK_WIDOW,
        SPIDER_MAN,
        THOR,
        LOKI,
        WOLVERINE,
        PROF_XAVIER,
        CYCLOPS,
        JEAN_GREY,
        BEAST,
        COLOSSUS,
        NIGHTCRAWLER,
        STORM,
        VISION,
        SCARLET_WITCH,
        FALCON,
        WINTER_SOLDIER,
        ADAM_WARLOCK,
        CAPTAIN_MARVEL,
        GROOT,
        GAMORA,
        ROCKET_RACCOON,
        STAR_LORD,
        MARIA_HILL,
        WAR_MACHINE,
        HAWKEYE,
        ANT_MAN,
        BLADE,
        GHOST_RIDER
    )

    fun addCardsOnManager(list: MutableList<CharacterModel>) {

        for (i in list.indices) {
            when (list[i].id) {

                STORM -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Ororo Munroe",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            2,
                            5,
                            4,
                            2,
                            3,
                            2,
                            list[i].description
                        )
                    )
                }

                NIGHTCRAWLER -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Kurt Wagner",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            5,
                            7,
                            7,
                            5,
                            7,
                            4,
                            list[i].description
                        )
                    )
                }

                COLOSSUS -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Piotr Nikolaievitch Rasputin",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            7,
                            5,
                            5,
                            4,
                            4,
                            7,
                            list[i].description
                        )
                    )
                }

                BEAST -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Henry P. Maccoy (Hank)",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            4,
                            1,
                            4,
                            6,
                            3,
                            4,
                            list[i].description
                        )
                    )
                }

                JEAN_GREY -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Jean Elaine Grey",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            3,
                            6,
                            6,
                            4,
                            3,
                            2,
                            list[i].description
                        )
                    )
                }

                CYCLOPS -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Scott Summers",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            3,
                            7,
                            3,
                            5,
                            4,
                            4,
                            list[i].description
                        )
                    )
                }

                PROF_XAVIER -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Charles Francis Xavier",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            4,
                            7,
                            4,
                            7,
                            2,
                            4,
                            list[i].description
                        )
                    )
                }

                WOLVERINE -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "James Howlett (Logan)",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            4,
                            1,
                            7,
                            2,
                            2,
                            4,
                            list[i].description
                        )
                    )
                }

                THANOS -> {
                    cardManager.add(
                        CardEntity(
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
                    )
                }

                STRANGE -> {
                    cardManager.add(
                        CardEntity(
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
                    )
                }

                CAPTAIN -> {
                    cardManager.add(
                        CardEntity(
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
                    )
                }

                NICK_FURY -> {
                    cardManager.add(
                        CardEntity(
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
                    )
                }

                IRON_MAN -> {
                    cardManager.add(
                        CardEntity(
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
                    )
                }

                BLACK_PANTHER -> {
                    cardManager.add(
                        CardEntity(
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
                    )
                }

                BLACK_WIDOW -> {
                    cardManager.add(
                        CardEntity(
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
                    )
                }

                SPIDER_MAN -> {
                    cardManager.add(
                        CardEntity(
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
                    )
                }

                THOR -> {
                    cardManager.add(
                        CardEntity(
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
                    )
                }

                LOKI -> {
                    cardManager.add(
                        CardEntity(
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
                    )
                }

                VISION -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Victor Shade",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            6,
                            6,
                            3,
                            4,
                            3,
                            5,
                            list[i].description
                        )
                    )
                }

                SCARLET_WITCH -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Wanda Maximoff",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            2,
                            6,
                            3,
                            3,
                            2,
                            2,
                            list[i].description
                        )
                    )
                }

                FALCON -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Sam Wilson",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            2,
                            1,
                            4,
                            2,
                            3,
                            2,
                            list[i].description
                        )
                    )
                }

                WINTER_SOLDIER -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Bucky Barnes",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            4,
                            1,
                            6,
                            2,
                            2,
                            4,
                            list[i].description
                        )
                    )
                }

                ADAM_WARLOCK -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Adam Warlock",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            6,
                            6,
                            2,
                            3,
                            7,
                            5,
                            list[i].description
                        )
                    )
                }

                CAPTAIN_MARVEL -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Carol Danvers",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            6,
                            6,
                            4,
                            3,
                            5,
                            5,
                            list[i].description
                        )
                    )
                }

                GROOT -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Groot",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            4,
                            3,
                            4,
                            3,
                            3,
                            7,
                            list[i].description
                        )
                    )
                }

                GAMORA -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Gamora",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            3,
                            1,
                            6,
                            3,
                            4,
                            3,
                            list[i].description
                        )
                    )
                }

                ROCKET_RACCOON -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Rocket Raccoon",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            2,
                            1,
                            4,
                            3,
                            2,
                            2,
                            list[i].description
                        )
                    )
                }

                STAR_LORD -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Rocket Raccoon",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            3,
                            1,
                            4,
                            4,
                            2,
                            3,
                            list[i].description
                        )
                    )
                }

                MARIA_HILL -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Maria Hill",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            2,
                            1,
                            4,
                            4,
                            2,
                            2,
                            list[i].description
                        )
                    )
                }

                WAR_MACHINE -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "James Rhodes",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            6,
                            6,
                            4,
                            3,
                            5,
                            6,
                            list[i].description
                        )
                    )
                }

                HAWKEYE -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Clint Barton",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            2,
                            1,
                            4,
                            2,
                            2,
                            2,
                            list[i].description
                        )
                    )
                }

                ANT_MAN -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Scott Lang",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            2,
                            3,
                            2,
                            4,
                            2,
                            2,
                            list[i].description
                        )
                    )
                }

                BLADE -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Eric Brooks",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            4,
                            1,
                            5,
                            2,
                            2,
                            4,
                            list[i].description
                        )
                    )
                }

                GHOST_RIDER -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Robbie Reyes",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            5,
                            3,
                            4,
                            2,
                            3,
                            4,
                            list[i].description
                        )
                    )
                }

            }
        }
    }

    fun getCardList() = cardManager

    fun getIdsList() = listOfIds

    companion object {
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
        const val WOLVERINE = 1009718
        const val PROF_XAVIER = 1009504
        const val CYCLOPS = 1009257
        const val JEAN_GREY = 1009327
        const val BEAST = 1009175
        const val COLOSSUS = 1009243
        const val NIGHTCRAWLER = 1009472
        const val STORM = 1009629
        const val VISION = 1009697
        const val SCARLET_WITCH = 1009562
        const val FALCON = 1009297
        const val WINTER_SOLDIER = 1010740
        const val ADAM_WARLOCK = 1010354
        const val CAPTAIN_MARVEL = 1010338
        const val GROOT = 1010743
        const val GAMORA = 1010763
        const val ROCKET_RACCOON = 1010744
        const val STAR_LORD = 1010733
        const val MARIA_HILL = 1011335
        const val WAR_MACHINE = 1017322
        const val HAWKEYE = 1009338
        const val ANT_MAN = 1010802
        const val BLADE = 1009191
        const val GHOST_RIDER = 1010925
    }
}