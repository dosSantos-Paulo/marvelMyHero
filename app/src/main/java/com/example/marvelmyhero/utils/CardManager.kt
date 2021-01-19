package com.example.marvelmyhero.utils

import com.example.marvelmyhero.card.model.CharacterModel
import com.example.marvelmyhero.card.model.Hero
import com.example.marvelmyhero.db.entity.CardEntity

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
        GHOST_RIDER,
        MANTIS,
        SHANG_CHI,
        DEADPOOL,
        KITTY_PRYDE,
        JUGGERNAUT,
        MYSTIQUE,
        MAGIK,
        INVISIBLE_WOMAN,
        THE_THING,
        HUMAN_TORCH,
        DAREDEVIL,
        VALKYRIE,
        JESSICA_JONES,
        LUKE_CAGE,
        IRON_FIST,
        HULK,
        ROGUE,
        GALACTUS,
        ODIN,
        SILVER_SURFER,
        MISTER_FANTASTIC,
        DOCTOR_DOOM
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
                            5,
                            5,
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
                            4,
                            6,
                            6,
                            6,
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
                            4,
                            7,
                            2,
                            4,
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
                            5,
                            7,
                            7,
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
                            5,
                            7,
                            7,
                            6,
                            4,
                            7,
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
                            4,
                            4,
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
                            7,
                            3,
                            7,
                            7,
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

                MANTIS -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "This One",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            7,
                            3,
                            6,
                            3,
                            7,
                            3,
                            list[i].description
                        )
                    )
                }

                SHANG_CHI -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Master of Kung Fu",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            4,
                            3,
                            7,
                            4,
                            4,
                            3,
                            list[i].description
                        )
                    )
                }

                DEADPOOL -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Wade Wilson",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            7,
                            7,
                            7,
                            2,
                            2,
                            4,
                            list[i].description
                        )
                    )
                }

                KITTY_PRYDE -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Anna Marie",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            3,
                            2,
                            4,
                            4,
                            2,
                            2,
                            list[i].description
                        )
                    )
                }

                JUGGERNAUT -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Cain Marko",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            7,
                            2,
                            4,
                            3,
                            5,
                            7,
                            list[i].description
                        )
                    )
                }

                MYSTIQUE -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Raven Darkhölme",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            4,
                            1,
                            5,
                            4,
                            2,
                            2,
                            list[i].description
                        )
                    )
                }

                MAGIK -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Illyana Rasputin(a)",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            2,
                            7,
                            4,
                            7,
                            3,
                            2,
                            list[i].description
                        )
                    )
                }

                INVISIBLE_WOMAN -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Susan S. Richards",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            6,
                            7,
                            5,
                            5,
                            5,
                            4,
                            list[i].description
                        )
                    )
                }

                THE_THING -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Benjamin Ben Grimm",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            6,
                            1,
                            4,
                            2,
                            2,
                            6,
                            list[i].description
                        )
                    )
                }

                HUMAN_TORCH -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Jonathan J. Storm",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            4,
                            6,
                            4,
                            4,
                            5,
                            4,
                            list[i].description
                        )
                    )
                }

                DAREDEVIL -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Matt Murdock",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            2,
                            4,
                            5,
                            3,
                            2,
                            3,
                            list[i].description
                        )
                    )
                }

                VALKYRIE -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Samantha Parrington",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            4,
                            1,
                            5,
                            3,
                            2,
                            5,
                            list[i].description
                        )
                    )
                }

                JESSICA_JONES -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Jessica Jones",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            4,
                            1,
                            3,
                            2,
                            3,
                            4,
                            list[i].description
                        )
                    )
                }

                LUKE_CAGE -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Luke Cage",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            5,
                            1,
                            4,
                            3,
                            2,
                            4,
                            list[i].description
                        )
                    )
                }

                IRON_FIST -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Danny Rand",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            3,
                            3,
                            6,
                            3,
                            2,
                            2,
                            list[i].description
                        )
                    )
                }

                HULK -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Bruce Benner",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            7,
                            6,
                            5,
                            6,
                            5,
                            7,
                            list[i].description
                        )
                    )
                }

                ROGUE -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Anna Marie",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            6,
                            6,
                            6,
                            6,
                            6,
                            6,
                            list[i].description
                        )
                    )
                }

                GALACTUS -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Galan",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            7,
                            6,
                            7,
                            7,
                            6,
                            7,
                            list[i].description
                        )
                    )
                }

                ODIN -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Odin Borson",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            7,
                            7,
                            4,
                            7,
                            7,
                            6,
                            list[i].description
                        )
                    )
                }

                SILVER_SURFER -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Norrin Radd",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            7,
                            7,
                            5,
                            4,
                            7,
                            6,
                            list[i].description
                        )
                    )
                }

                MISTER_FANTASTIC -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Reed Richards",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            6,
                            2,
                            3,
                            7,
                            3,
                            3,
                            list[i].description
                        )
                    )
                }

                DOCTOR_DOOM -> {
                    cardManager.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Victor Von Doom",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            6,
                            6,
                            4,
                            6,
                            5,
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

    fun random5(cardManager: MutableList<Hero>): MutableList<Hero> {
        val list: MutableList<Hero> = mutableListOf()

        val bronzeCards: MutableList<Hero> = mutableListOf()
        val silverCards: MutableList<Hero> = mutableListOf()
        val goldCards: MutableList<Hero> = mutableListOf()
        val diamondCards: MutableList<Hero> = mutableListOf()

        cardManager.forEach {

            if (it.classification < 3.0) {
                bronzeCards.add(it)
            } else if (it.classification >= 3.0 && it.classification < 4.6) {
                silverCards.add(it)
            } else if (it.classification >= 4.6 && it.classification < 6.0) {
                goldCards.add(it)
            } else {
                diamondCards.add(it)
            }
        }

        for (i in 1..3) {

            var validator: Boolean
            var card: Hero

            do {

                validator = true

                card = bronzeCards.random()
                list.forEach {
                    if (card.id == it.id) {
                        validator = false
                    }
                }

            } while (!validator)

            list.add(card)

        }

        list.add(silverCards.random())

        list.add(goldCards.random())

        return list

    }

    fun random3(cardManager: MutableList<Hero>): MutableList<Hero> {

        val listOf3: MutableList<Hero> = mutableListOf()
        val list: MutableList<Hero> = mutableListOf()

        val bronzeCards: MutableList<Hero> = mutableListOf()
        val silverCards: MutableList<Hero> = mutableListOf()
        val goldCards: MutableList<Hero> = mutableListOf()
        val diamondCards: MutableList<Hero> = mutableListOf()

        cardManager.forEach {

            if (it.classification < 3.0) {
                bronzeCards.add(it)
            } else if (it.classification >= 3.0 && it.classification < 4.6) {
                silverCards.add(it)
            } else if (it.classification >= 4.6 && it.classification < 6.0) {
                goldCards.add(it)
            } else {
                diamondCards.add(it)
            }
        }

        for (i in 1..4) {
            list.add(bronzeCards.random())
        }
        for (i in 1..3) {
            list.add(silverCards.random())
        }
        for (i in 1..2) {
            list.add(goldCards.random())
        }
        list.add(diamondCards.random())

        for (i in 1..3) {

            var validator: Boolean
            var card: Hero

            do {

                validator = true

                card = list.random()
                listOf3.forEach {
                    if (card.id == it.id) {
                        validator = false
                    }
                }

            } while (!validator)

            listOf3.add(card)
        }

        return listOf3

    }

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
        const val MANTIS = 1011026
        const val SHANG_CHI = 1009577
        const val DEADPOOL = 1009268
        const val KITTY_PRYDE = 1009508
        const val JUGGERNAUT = 1009382
        const val MYSTIQUE = 1009465
        const val MAGIK = 1009415
        const val INVISIBLE_WOMAN = 1009366
        const val THE_THING = 1009662
        const val HUMAN_TORCH = 1009356
        const val DAREDEVIL = 1009262
        const val VALKYRIE = 1010350
        const val JESSICA_JONES = 1009378
        const val LUKE_CAGE = 1009215
        const val IRON_FIST = 1009367
        const val HULK = 1009351
        const val ROGUE = 1009546
        const val GALACTUS = 1009312
        const val ODIN = 1009480
        const val SILVER_SURFER = 1009592
        const val MISTER_FANTASTIC = 1009459
        const val DOCTOR_DOOM = 1009281
    }
}