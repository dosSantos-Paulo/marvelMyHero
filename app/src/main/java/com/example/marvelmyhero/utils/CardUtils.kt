package com.example.marvelmyhero.utils

import com.example.marvelmyhero.login.model.CardManipulator
import com.example.marvelmyhero.card.model.CharacterModel
import com.example.marvelmyhero.database.entity.CardEntity

class CardUtils {

    fun addCardOnManager(list: MutableList<CharacterModel>) {

        cardManipulator

        val value = cardManipulator.getAllCards().size

        for (i in list.indices) {
            when (list[i].id) {

                com.example.marvelmyhero.utils.CardManager.STORM -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.NIGHTCRAWLER -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.COLOSSUS -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.BEAST -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.JEAN_GREY -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.CYCLOPS -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.PROF_XAVIER -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.WOLVERINE -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.THANOS -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.STRANGE -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.CAPTAIN -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.NICK_FURY -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.IRON_MAN -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.BLACK_PANTHER -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.BLACK_WIDOW -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.SPIDER_MAN -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.THOR -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.LOKI -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.VISION -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.SCARLET_WITCH -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.FALCON -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.WINTER_SOLDIER -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.ADAM_WARLOCK -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.CAPTAIN_MARVEL -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.GROOT -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.GAMORA -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.ROCKET_RACCOON -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.STAR_LORD -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.MARIA_HILL -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.WAR_MACHINE -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.HAWKEYE -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.ANT_MAN -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.BLADE -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.GHOST_RIDER -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.MANTIS -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.SHANG_CHI -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.DEADPOOL -> {
                    cardManipulator.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Wade Wilson",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            4,
                            1,
                            6,
                            2,
                            7,
                            4,
                            list[i].description
                        )
                    )
                }

                com.example.marvelmyhero.utils.CardManager.KITTY_PRYDE -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.JUGGERNAUT -> {
                    cardManipulator.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Cain Marko",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            7,
                            1,
                            3,
                            2,
                            4,
                            7,
                            list[i].description
                        )
                    )
                }

                com.example.marvelmyhero.utils.CardManager.MYSTIQUE -> {
                    cardManipulator.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "Raven Darkhölme",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            4,
                            0,
                            5,
                            4,
                            2,
                            2,
                            list[i].description
                        )
                    )
                }

                com.example.marvelmyhero.utils.CardManager.MAGIK -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.INVISIBLE_WOMAN -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.THE_THING -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.HUMAN_TORCH -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.SANDMAN -> {
                    cardManipulator.add(
                        CardEntity(
                            list[i].id,
                            list[i].name,
                            "William Baker",
                            "${list[i].thumbnail.path}.${list[i].thumbnail.extension}",
                            7,
                            4,
                            5,
                            4,
                            4,
                            5,
                            list[i].description
                        )
                    )
                }

                com.example.marvelmyhero.utils.CardManager.DAREDEVIL -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.VALKYRIE -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.JESSICA_JONES -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.LUKE_CAGE -> {
                    cardManipulator.add(
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

                com.example.marvelmyhero.utils.CardManager.IRON_FIST -> {
                    cardManipulator.add(
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
            }
        }
    }

    companion object {
        val cardManipulator = CardManipulator()
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