package com.example.marvelmyhero.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelmyhero.R
import com.example.marvelmyhero.card.view.CardFrontFragment
import com.example.marvelmyhero.model.Hero
import com.google.android.material.card.MaterialCardView

class MyDeckActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_deck)

        val arrowBack = findViewById<ImageView>(R.id.img_arrowBack_myDeck)
        val materialCardView = findViewById<MaterialCardView>(R.id.materialCard_myDeck)

        arrowBack.setOnClickListener {
            finish()
        }

        val cardRecyclerView = findViewById<RecyclerView>(R.id.recyclerView_myDeck)
        val cardManager = GridLayoutManager(this, DECK_COLLUMN)
        val cardAdapter = MyDeckAdapter(testCardColection(10)) {


            supportFragmentManager.beginTransaction().apply {

                setCustomAnimations(
                    R.anim.from_left_user_card,
                    R.anim.to_left_user_card,
                    R.anim.from_left_user_card,
                    R.anim.to_left_user_card
                )
                replace(R.id.frameLayout_myDeck, CardFrontFragment())
                addToBackStack(null)
                commit()
            }
        }

        cardRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = cardManager
            adapter = cardAdapter
        }

    }

    companion object {
        const val DECK_COLLUMN = 3

        fun testCardColection (quantidade: Int) :MutableList<Hero> {
            val cardList: MutableList<Hero> = mutableListOf()
            for (i in 0 until quantidade) {
                cardList.add(
                    Hero(
                        1,
                        "Dr. Strange",
                        "Stephen Strange",
                        R.drawable.dr_strange,
                        3,
                        6,
                        3,
                        4,
                        7,
                        2,
                        "Nothing"
                    )
                )

                cardList.add(
                    Hero(
                        2,
                        "Captain America",
                        "Steve Rogers",
                        R.drawable.captain_america,
                        3,
                        1,
                        6,
                        3,
                        2,
                        3,
                        "Cap"
                    )

                )
            }
            return cardList
        }
    }

}