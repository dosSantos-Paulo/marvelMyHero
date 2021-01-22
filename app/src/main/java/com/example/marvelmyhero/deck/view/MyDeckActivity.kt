package com.example.marvelmyhero.deck.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelmyhero.R
import com.example.marvelmyhero.card.view.CardFrontFragment
import com.example.marvelmyhero.utils.UserCardUtils.Companion.NEW_USER
import com.google.android.material.card.MaterialCardView

class MyDeckActivity : AppCompatActivity() {

    private val deck = NEW_USER.getDeck()
    private val arrowBack: ImageView by lazy { findViewById(R.id.img_arrowBack_myDeck) }
    private val info: ImageView by lazy { findViewById(R.id.ic_info) }
    private val cardRecyclerView: RecyclerView by lazy { findViewById(R.id.recyclerView_myDeck) }
    private val cardManager = GridLayoutManager(this, DECK_COLLUMN)

    private val cardAdapter = MyDeckAdapter(deck) {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_deck)

        arrowBack.setOnClickListener {
            finish()
        }

        info.setOnClickListener {
            val intent = Intent(this, InfoMyDeckActivity::class.java)
            startActivity(intent)
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