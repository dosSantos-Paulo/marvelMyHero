package com.example.marvelmyhero.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.marvelmyhero.R
import com.example.marvelmyhero.card.view.CardFrontFragment.Companion.getClassification
import com.example.marvelmyhero.card.view.MiniCardFragment
import com.example.marvelmyhero.data.repository.CharacterRepository
import com.example.marvelmyhero.login.view.LoginActivity
import com.example.marvelmyhero.login.view.LoginFragment.Companion.EMAIL_PREFS
import com.example.marvelmyhero.login.view.LoginFragment.Companion.KEEP_CONNECTED_PREFS
import com.example.marvelmyhero.login.view.LoginFragment.Companion.PASS_PREFS
import com.example.marvelmyhero.model.CharacterModel
import com.example.marvelmyhero.model.Hero
import com.example.marvelmyhero.model.User
import com.example.marvelmyhero.viewmodel.CharacterViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.squareup.picasso.Picasso
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val allCharId = listOf<Int>(1009652, 1017300, 1009220, 1009471, 1009368)


        val viewModel = ViewModelProvider(
            this,
            CharacterViewModel.CharacterViewModelFactory(CharacterRepository())
        ).get(CharacterViewModel::class.java)

        viewModel.getCharacter(allCharId).observe(this) {
            val deck = it

            val thanos = Hero(
                3,
                deck[0].name,
                "The Mad Titan",
                "${deck[0].thumbnail.path}.${deck[0].thumbnail.extension}",
                6,
                6,
                4,
                6,
                7,
                7,
                deck[0].description
            )

            val strange = Hero(
                2,
                deck[1].name,
                "Stephen Strange",
                "${deck[1].thumbnail.path}.${deck[1].thumbnail.extension}",
                3,
                6,
                3,
                4,
                7,
                2,
                deck[1].description
            )

            val captain = Hero(
                3,
                deck[2].name,
                "Steve Rogers",
                "${deck[2].thumbnail.path}.${deck[2].thumbnail.extension}",
                3, 1, 6, 3, 2, 3,
                deck[2].description
            )

            val nickFury = Hero(
                4,
                deck[3].name,
                "Nicholas Joseph Fury Jr.",
                "${deck[3].thumbnail.path}.${deck[3].thumbnail.extension}",
                2, 1, 6, 3, 2, 2,
                deck[3].description
            )

            val ironMan = Hero(
                5,
                deck[4].name,
                "Anthony Edward Stark",
                "${deck[4].thumbnail.path}.${deck[4].thumbnail.extension}",
                6, 6, 4, 6, 5, 6,
                deck[4].description
            )

            val user = User(
                "Stan Lee",
                "Stanley Martin Lieber",
                "stanlee@gmail.com",
                "marvel",
                R.drawable.stan_lee,
            )
            newCardAlert(mutableListOf(thanos, strange, captain, nickFury, ironMan))
            user.deck = mutableListOf(thanos, strange, captain, nickFury, ironMan)
            user.team = mutableListOf(thanos, captain, ironMan)

            val getDeck = mutableListOf<Hero>()


            val userButton = findViewById<ImageView>(R.id.img_userIcon_main)
            val exitButton = findViewById<ImageView>(R.id.ic_exit_main)
            val deckButton = findViewById<MaterialButton>(R.id.btn_myDeck_main)
            val materialCardView = findViewById<MaterialCardView>(R.id.materialCardView_main)


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

            materialCardView.setOnClickListener {
                val intent = Intent(this, MyTeamActivity::class.java)
                startActivity(intent)
            }
        }


//        Novos Cards


//        Modificar para usuário que virá do login


//        Modificar apos o Alerta de novas cartas


    }

    private fun newCardAlert(cardList: MutableList<Hero>) {

        MaterialAlertDialogBuilder(this)
            .setTitle("New Cards")
            .setMessage("You won a new cards, would you like to see them?")
            .setPositiveButton("Yes") { _, _ ->

                showNewCard(cardList)

            }
            .setNegativeButton("No") { _, _ ->
                closeContextMenu()
            }
            .show()
    }


    fun showNewCard(cardList: MutableList<Hero>) {

        for (i in 0..4) {
            var getStars: String = ""
            val starValue = getString(R.string.classificationStar)

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

            MaterialAlertDialogBuilder(this)

                .setMessage("Name : ${cardList[i].heroName} \n Classification: $getStars ")
                .setNeutralButton("OK") { _, _ ->
                    closeContextMenu()
                }
                .show()
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
                val keepConnectedPreferences =
                    getSharedPreferences(KEEP_CONNECTED_PREFS, MODE_PRIVATE)

                keepConnectedPreferences.edit()
                    .putString(EMAIL_PREFS, "")
                    .putString(PASS_PREFS, "")
                    .apply()

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)

                finish()
            }
            .show()
    }

    companion object {
        const val USER_KEY = "USER"
    }
}