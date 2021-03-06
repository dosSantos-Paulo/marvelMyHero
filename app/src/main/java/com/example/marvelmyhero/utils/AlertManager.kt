package com.example.marvelmyhero.utils

import android.app.Activity
import com.example.marvelmyhero.R
import com.example.marvelmyhero.card.model.Hero
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AlertManager(val view: Activity) {

    fun newCardAlert(
        cardManager: CardManager,
        fullList: MutableList<Hero>,
        isRandom: Boolean
    ): MutableList<Hero> {
        val randomList = mutableListOf<Hero>()

        if (isRandom) {
            cardManager.random3(fullList).forEach {
                randomList.add(it)
            }
        } else if (!isRandom) {
            randomList.addAll(fullList)
        }

        MaterialAlertDialogBuilder(view)
            .setTitle("New Cards")
            .setMessage("You won a new cards, would you like to see them?")
            .setPositiveButton("Yes") { _, _ ->

                showNewCard(randomList)
            }
            .setNegativeButton("No") { _, _ ->
                view.closeContextMenu()
            }
            .show()

        return randomList
    }

    private fun showNewCard(cardList: MutableList<Hero>) {

        cardList.forEach {
            var getStars = ""
            val starValue = view.getString(R.string.classificationStar)

            when (it.classification) {
                in 0.1..2.9 -> {
                    getStars = starValue
                }
                in 3.0..4.5 -> {
                    getStars = "$starValue $starValue"
                }
                in 4.6..5.9 -> {
                    getStars = "$starValue $starValue $starValue"
                }
                in 6.0..7.0 -> {
                    getStars = "$starValue $starValue $starValue $starValue"
                }
            }

            MaterialAlertDialogBuilder(view)

                .setMessage("Name : ${it.heroName} \n Classification: $getStars ")
                .setPositiveButton("OK") { _, _ ->
                    view.closeContextMenu()
                }
                .show()
        }
    }
}