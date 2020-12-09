package com.example.marvelmyhero.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelmyhero.R
import com.example.marvelmyhero.card.view.MiniCardFragment
import com.example.marvelmyhero.model.Hero
import com.example.marvelmyhero.view.MyDeckActivity.Companion.testCardColection
import com.google.android.material.button.MaterialButton

class MyTeamActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_team)

//        criando novo deck ** a resolver

        val thanos = Hero(
            1,
            "Thanos",
            "The Mad Titan",
            "null",
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
            "null",
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
            "null",
            3, 1, 6, 3, 2, 3,
            "From the dark days of world war to the explosive challenges of " +
                    "today, Super-Soldier Captain America stands ready as a shining sentinel " +
                    "of liberty to shield the oppressed and fight for freedom everywhere."
        )

        val nickFury = Hero(
            4,
            "Nick Fury",
            "Nicholas Joseph Fury Jr.",
            "null",
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
            "null",
            6, 6, 4, 6, 5, 6,
            "Tony Stark is the wealthy son of industrialist and weapons manufacturer " +
                    "Howard Stark and his wife, Maria. Tony grew up a genius with a brilliant " +
                    "mind for technology and inventions and, naturally, followed in his father’s " +
                    "footsteps, inheriting Stark Industries upon his parents’ untimely death. " +
                    "Tony designed many weapons of war for Stark Industries, far beyond what any " +
                    "other company was creating, while living the lifestyle of a bon vivant."
        )

        val deck = mutableListOf(thanos, strange, captain, nickFury, ironMan)
        val team = mutableListOf(thanos, captain, ironMan)

        val backArrowButton = findViewById<ImageView>(R.id.img_arrowBack_myTeam)

        val recyclerViewMyTeam = findViewById<RecyclerView>(R.id.recyclerView_myTeam)
        val viewManagerMyTeam = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val adapterMyTeam = MyTeamAdapter(deck) {
            Toast.makeText(this, getString(R.string.text_inserir_cards), Toast.LENGTH_LONG).show()
        }

        recyclerViewMyTeam.apply {
            setHasFixedSize(true)
            layoutManager = viewManagerMyTeam
            adapter = adapterMyTeam

        }

        miniCardFragment(
            team[0].heroName,
            team[0].imageUrl,
            team[0].classification,
            R.id.frameLayout_teamCard1_myTeam
        )

        miniCardFragment(
            team[1].heroName,
            team[1].imageUrl,
            team[1].classification,
            R.id.frameLayout_teamCard2_myTeam
        )

        miniCardFragment(
            team[2].heroName,
            team[2].imageUrl,
            team[2].classification,
            R.id.frameLayout_teamCard3_myTeam
        )

        backArrowButton.setOnClickListener {
            finish()
        }

    }

    private fun miniCardFragment(
        name: String,
        imageUrl: String,
        classification: Double,
        frame: Int
    ) {
        val newCard = MiniCardFragment(name, imageUrl, classification)
        supportFragmentManager.beginTransaction().apply {
            replace(frame, newCard)
            commit()
        }
    }
}