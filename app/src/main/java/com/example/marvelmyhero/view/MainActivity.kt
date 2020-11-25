package com.example.marvelmyhero.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.marvelmyhero.R
import com.example.marvelmyhero.card.view.MiniCardFragment
import com.example.marvelmyhero.model.Hero
import com.example.marvelmyhero.model.User
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Novos Cards
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

//        Modificar para usuário que virá do login
        val user = User(
            "Stan Lee",
            "Stanley Martin Lieber",
            "stanlee@gmail.com",
            "marvel",
            R.drawable.stan_lee,
        )

//        Modificar apos o Alerta de novas cartas

        val getDeck = mutableListOf<Hero>()

        user.deck = mutableListOf(thanos, strange, captain, nickFury, ironMan)
        user.team = mutableListOf(thanos, captain, ironMan)

        val userButton = findViewById<ImageView>(R.id.img_userIcon_main)
        val exitButton = findViewById<ImageView>(R.id.ic_exit_main)
        val deckButton = findViewById<MaterialButton>(R.id.btn_myDeck_main)
        val userImage = findViewById<ImageView>(R.id.img_userIcon_main)
        val userName = findViewById<TextView>(R.id.txt_userName_main)

        Picasso.get().load(user.imageUrl).into(userImage)
        userName.text = user.nickName


        miniCardFragment(
            user.team[0].heroName,
            user.team[0].imageUrl,
            user.team[0].classification,
            R.id.frameLayout_teamCard1_main
        )

        miniCardFragment(
            user.team[1].heroName,
            user.team[1].imageUrl,
            user.team[1].classification,
            R.id.frameLayout_teamCard2_main
        )

        miniCardFragment(
            user.team[2].heroName,
            user.team[2].imageUrl,
            user.team[2].classification,
            R.id.frameLayout_teamCard3_main
        )

        userButton.setOnClickListener {
            newUserFragment(user)
        }

        exitButton.setOnClickListener {
            exitDialog()
        }

        deckButton.setOnClickListener {
            val intent = Intent(this, MyDeckActivity::class.java)
            startActivity(intent)
        }
    }

    private fun miniCardFragment(
        name: String,
        imageUrl: Int,
        classification: Double,
        frame: Int
    ) {
        val newCard = MiniCardFragment(name, imageUrl, classification)
        supportFragmentManager.beginTransaction().apply {
            replace(frame, newCard)
            commit()
        }
    }

    private fun newUserFragment(user: User) {

        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(
                R.anim.from_left_user_card,
                R.anim.to_left_user_card,
                R.anim.from_left_user_card,
                R.anim.to_left_user_card
            )
            replace(R.id.frameLayout_userDetail_main, UserFragment(user))
            addToBackStack(null)
            commit()
        }
    }

    private fun exitDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.exitDialog_title))
            .setMessage(getString(R.string.exitDialog_message))
            .setNegativeButton(getString(R.string.exitDialog_negativeButton)) { _, _ ->
                closeContextMenu()
            }
            .setPositiveButton(getString(R.string.exitDialog_positiveButton)) { _, _ ->
                finish()
            }
            .show()
    }

    companion object {
        const val USER_KEY = "USER"
    }
}