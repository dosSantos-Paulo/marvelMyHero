package com.example.marvelmyhero.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelmyhero.R
import com.example.marvelmyhero.card.view.CardFrontFragment
import com.example.marvelmyhero.data.repository.CharacterRepository
import com.example.marvelmyhero.model.CardManager
import com.example.marvelmyhero.model.Hero
import com.example.marvelmyhero.utils.CardUtils.Companion.CARD_MANAGER
import com.example.marvelmyhero.view.MainActivity.Companion.USER_KEY
import com.example.marvelmyhero.viewmodel.CharacterViewModel
import com.google.android.material.card.MaterialCardView

class MyDeckActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_deck)

        val deck = CARD_MANAGER.getAllCards()

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