package com.example.marvelmyhero.deck.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.marvelmyhero.R
import com.example.marvelmyhero.card.model.Hero
import com.example.marvelmyhero.card.view.MiniCardFragment

class InfoMyDeckActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_my_deck)

        val arrowBack = findViewById<ImageView>(R.id.img_arrowBack_Info)

        arrowBack.setOnClickListener {
            finish()
        }

        val bronzeHero = Hero(1,
            "Nick Fury",
            "Nick Fury",
            "http://i.annihil.us/u/prod/marvel/i/mg/3/c0/5261a745e658d.jpg",
            1,
            2,
            2,
            2,
            1,
            1,
            "Nick")

        val silverHero = Hero(2,
            "Spider-Man",
            "Spider-Man",
            "http://i.annihil.us/u/prod/marvel/i/mg/3/50/526548a343e4b.jpg",
            3,
            1,
            4,
            4,
            3,
            4,
            "Spider Man")

        val goldHero = Hero(3,
            "Thor",
            "Thor",
            "http://i.annihil.us/u/prod/marvel/i/mg/d/d0/5269657a74350.jpg",
            6,
            6,
            4,
            2,
            7,
            7,
            "Thor")

        val diamondHero = Hero(4,
            "Thanos",
            "Thanos",
            "http://i.annihil.us/u/prod/marvel/i/mg/6/40/5274137e3e2cd.jpg",
            6,
            6,
            4,
            6,
            7,
            7,
            "Thanos")

        showTeamCards(mutableListOf(bronzeHero, silverHero, goldHero, diamondHero))

    }

    private fun showTeamCards(team: MutableList<Hero>) {

        for (i in team.indices) {

            var frameLayout = R.id.frameLayout_info_card1

            when (i) {
                0 -> frameLayout = R.id.frameLayout_info_card1

                1 -> frameLayout = R.id.frameLayout_info_card2

                2 -> frameLayout = R.id.frameLayout_info_card3

                3 -> frameLayout = R.id.frameLayout_info_card4
            }

            var txtCard = findViewById<TextView>(R.id.txtInfoCard1)

            when (i) {
                0 -> txtCard = findViewById(R.id.txtInfoCard1)

                1 -> txtCard = findViewById(R.id.txtInfoCard2)

                2 -> txtCard = findViewById(R.id.txtInfoCard3)

                3 -> txtCard = findViewById(R.id.txtInfoCard4)
            }

            when (team[i].classification) {
                in 0.1..2.9 -> txtCard.text = getString(R.string.bronze)
                in 3.0..4.5 -> txtCard.text = getString(R.string.silver)
                in 4.6..5.9 -> txtCard.text = getString(R.string.gold)
                in 6.0..7.0 -> txtCard.text = getString(R.string.diamond)
            }

            miniCardFragment(
                team[i].heroName,
                team[i].imageUrl,
                team[i].classification,
                frameLayout
            )
        }
    }

    private fun miniCardFragment(
        name: String,
        imageUrl: String,
        classification: Double,
        frame: Int,
    ) {
        val newCard = MiniCardFragment(name, imageUrl, classification)
        supportFragmentManager.beginTransaction().apply {
            replace(frame, newCard)
            commit()
        }
    }
}