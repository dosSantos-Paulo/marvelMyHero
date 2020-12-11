package com.example.marvelmyhero.deck.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelmyhero.R
import com.example.marvelmyhero.card.view.CardFrontFragment
import com.example.marvelmyhero.utils.UserCardUtils.Companion.NEW_USER
import com.google.android.material.card.MaterialCardView

class MyDeckActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_deck)

        val deck = NEW_USER.getDeck()

        val arrowBack = findViewById<ImageView>(R.id.img_arrowBack_myDeck)
        val materialCardView = findViewById<MaterialCardView>(R.id.materialCard_myDeck)

        arrowBack.setOnClickListener {
            finish()
        }

        val cardRecyclerView = findViewById<RecyclerView>(R.id.recyclerView_myDeck)
        val cardManager = GridLayoutManager(this, DECK_COLLUMN)
        val cardAdapter = MyDeckAdapter(deck) {

            supportFragmentManager.beginTransaction().apply {
                setCustomAnimations(
                    R.anim.show_card,
                    R.anim.hide_card,
                    R.anim.show_card,
                    R.anim.hide_card,
                )
                replace(R.id.frameLayout_myDeck, CardFrontFragment(it))
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
    }

}