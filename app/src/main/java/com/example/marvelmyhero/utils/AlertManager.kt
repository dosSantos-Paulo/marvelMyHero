package com.example.marvelmyhero.utils

import com.example.marvelmyhero.R
import com.example.marvelmyhero.card.model.Hero
import com.example.marvelmyhero.main.view.MainActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AlertManager(val view: MainActivity) {

     fun newCardAlert(cardManager: CardManager, fullList: MutableList<Hero>): MutableList<Hero> {
        val randomList = mutableListOf<Hero>()

//        Implementar lógica de validação de dias
        val todayIsAnotherDay: Boolean = true

        if (todayIsAnotherDay) {
            cardManager.random5(fullList).forEach {
                randomList.add(it)
            }
        } else if (!todayIsAnotherDay) {
            cardManager.random3(fullList).forEach{
                randomList.add(it)
            }
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

        for (i in 0..4) {
            var getStars = ""
            val starValue = view.getString(R.string.classificationStar)

            when (cardList[i].classification) {
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

                .setMessage("Name : ${cardList[i].heroName} \n Classification: $getStars ")
                .setPositiveButton("OK") { _, _ ->
                    view.closeContextMenu()
                }
                .show()
        }
    }

}