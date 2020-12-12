package com.example.marvelmyhero.main.view

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.marvelmyhero.R
import com.example.marvelmyhero.card.view.MiniCardFragment
import com.example.marvelmyhero.login.view.LoginActivity
import com.example.marvelmyhero.login.view.LoginFragment.Companion.EMAIL_PREFS
import com.example.marvelmyhero.login.view.LoginFragment.Companion.KEEP_CONNECTED_PREFS
import com.example.marvelmyhero.login.view.LoginFragment.Companion.PASS_PREFS
import com.example.marvelmyhero.card.model.Hero
import com.example.marvelmyhero.deck.view.MyDeckActivity
import com.example.marvelmyhero.developers.view.DevelopersActivity
import com.example.marvelmyhero.login.model.User
import com.example.marvelmyhero.utils.CardUtils.Companion.CARD_MANAGER
import com.example.marvelmyhero.utils.UserCardUtils.Companion.NEW_USER
import com.example.marvelmyhero.utils.UserUtils
import com.example.marvelmyhero.team.view.MyTeamActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val exitButton = findViewById<ImageView>(R.id.ic_exit_main)
        val deckButton = findViewById<MaterialButton>(R.id.btn_myDeck_main)
        val materialCardView = findViewById<MaterialCardView>(R.id.materialCardView_main)
        val keepConnectedPreferences = getSharedPreferences(KEEP_CONNECTED_PREFS, MODE_PRIVATE)
        val user = getUser(keepConnectedPreferences)
        val developers = findViewById<ImageView>(R.id.img_developers)

        toolBarItems(user)

        NEW_USER.setUser(user)
        NEW_USER.addOnDeck(CARD_MANAGER.getAllCards())
        showTeamCards(NEW_USER.getTeam())

        exitButton.setOnClickListener {
            exitDialog(keepConnectedPreferences)
        }

        deckButton.setOnClickListener {
            val intent = Intent(this, MyDeckActivity::class.java)
            startActivity(intent)
        }

        developers.setOnClickListener {
            val intent = Intent(this, DevelopersActivity::class.java)
            startActivity(intent)
        }

        materialCardView.setOnClickListener {
            val intent = Intent(this, MyTeamActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showTeamCards(team: MutableList<Hero>) {

        for (i in team.indices) {
            var frameLayout = R.id.frameLayout_teamCard1_main
            when (i) {
                1 -> frameLayout = R.id.frameLayout_teamCard2_main

                2 -> frameLayout = R.id.frameLayout_teamCard3_main
            }

            miniCardFragment(
                team[i].heroName,
                team[i].imageUrl,
                team[i].classification,
                frameLayout
            )
        }
    }

    private fun toolBarItems(user: User) {
        val userImage = findViewById<ImageView>(R.id.img_userIcon_main)
        val userName = findViewById<TextView>(R.id.txt_userName_main)

        Picasso.get().load(user.imageUrl).into(userImage)
        userName.text = user.nickName

        userImage.setOnClickListener {
            newUserFragment(user)
        }
    }

    private fun getUser(keepConnectedPreferences: SharedPreferences): User {
        val email = keepConnectedPreferences.getString(EMAIL_PREFS, "")
        val password = keepConnectedPreferences.getString(PASS_PREFS, "")
        return UserUtils.USER_MANAGER.login(email, password)!!
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

    private fun exitDialog(keepConnectedPreferences: SharedPreferences) {
        MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.exitDialog_title))
            .setMessage(getString(R.string.exitDialog_message))
            .setNegativeButton(getString(R.string.exitDialog_negativeButton)) { _, _ ->
                closeContextMenu()
            }
            .setPositiveButton(getString(R.string.exitDialog_positiveButton)) { _, _ ->

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