package com.example.marvelmyhero.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelmyhero.R
import com.example.marvelmyhero.card.view.CardFrontFragment
import com.example.marvelmyhero.model.Hero
import com.example.marvelmyhero.view.MainActivity.Companion.USER_KEY
import com.google.android.material.card.MaterialCardView

class MyDeckActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_deck)

//        criando novo deck ** a resolver

        val thanos = Hero(
            1,
            "Thanos",
            "The Mad Titan",
            R.drawable.thanos,
            6,
            6,
            4,
            6,
            7,
            7,
            "He is perhaps the most evil, bloodthirsty, and powerful villain in the universe—so " +
                    "powerful that religious sects have worshipped him as a god. His enemies include nearly " +
                    "every Super Hero in existence. He is obsessed with Goddess Death and has waged genocidal " +
                    "campaigns in an attempt to placate her. Most famously, he has attempted to conquer the " +
                    "universe by wielding the Infinity Gauntlet. He is the Mad Titan.\n" +
                    "\n" +
                    "He is Thanos."
        )

        val strange = Hero(
            2,
            "Dr. Strange",
            "Stephen Strange",
            R.drawable.dr_strange,
            3,
            6,
            3,
            4,
            7,
            2,
            "As Earth’s Sorcerer Supreme, Doctor Strange wields arcane " +
                    "spells and mystical artifacts to defend the planet against malevolent threats."
        )

        val captain = Hero(
            3,
            "Captain America",
            "Steve Rogers",
            R.drawable.captain_america,
            3, 1, 6, 3, 2, 3,
            "From the dark days of world war to the explosive challenges of " +
                    "today, Super-Soldier Captain America stands ready as a shining sentinel " +
                    "of liberty to shield the oppressed and fight for freedom everywhere."
        )

        val nickFury = Hero(
            4,
            "Nick Fury",
            "Nicholas Joseph Fury Jr.",
            R.drawable.fury,
            2, 1, 6, 3, 2, 2,
            "Raised by a single mother and living a normal life as a U.S. Army Ranger, " +
                    "Marcus Johnson only began using his current moniker when he discovered that his " +
                    "birth father was none other than legendary super-spy Nick Fury. Already a trained " +
                    "combat veteran with a host of covert experiences under his belt, Nick Fury Jr. claimed " +
                    "his birthright and started a new life in espionage, following in his old man’s footsteps as an agent of S.H.I.E.L.D."
        )

        val ironMan = Hero(
            5,
            "Tony Stark",
            "Anthony Edward Stark",
            R.drawable.iron_man,
            6, 6, 4, 6, 5, 6,
            "Tony Stark is the wealthy son of industrialist and weapons manufacturer " +
                    "Howard Stark and his wife, Maria. Tony grew up a genius with a brilliant " +
                    "mind for technology and inventions and, naturally, followed in his father’s " +
                    "footsteps, inheriting Stark Industries upon his parents’ untimely death. " +
                    "Tony designed many weapons of war for Stark Industries, far beyond what any " +
                    "other company was creating, while living the lifestyle of a bon vivant."
        )

        val deck = mutableListOf(thanos, strange, captain, nickFury, ironMan)

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