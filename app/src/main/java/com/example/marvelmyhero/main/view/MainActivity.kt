package com.example.marvelmyhero.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.marvelmyhero.R
import com.example.marvelmyhero.card.view.MiniCardFragment
import com.example.marvelmyhero.card.model.Hero
import com.example.marvelmyhero.db.database.AppDataBase
import com.example.marvelmyhero.db.entity.CardEntity
import com.example.marvelmyhero.db.repository.CardRepository
import com.example.marvelmyhero.db.viewmodel.CardViewModel
import com.example.marvelmyhero.deck.view.MyDeckActivity
import com.example.marvelmyhero.developers.view.DevelopersActivity
import com.example.marvelmyhero.login.model.User
import com.example.marvelmyhero.utils.UserCardUtils.Companion.NEW_USER
import com.example.marvelmyhero.team.view.MyTeamActivity
import com.example.marvelmyhero.utils.CardManager
import com.facebook.internal.Mutable
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private lateinit var databaseViewModel: CardViewModel

    private val cardManager = CardManager()

    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val exitButton = findViewById<ImageView>(R.id.ic_exit_main)
        val deckButton = findViewById<MaterialButton>(R.id.btn_myDeck_main)
        val materialCardView = findViewById<MaterialCardView>(R.id.materialCardView_main)
        val developers = findViewById<ImageView>(R.id.img_developers)

        databaseViewModel = ViewModelProvider(
            this,
            CardViewModel.CardViewModelFactory(
                CardRepository(
                    AppDataBase.getDatabase(this).cardDao()
                )
            )
        ).get(CardViewModel::class.java)


        user = toolBarItems(User(
            "Teste",
            "Testando",
            "Teste@Teste",
            "123",
            R.drawable.ic_perfil)
        )


        getAllCardsFromDB(user)


        exitButton.setOnClickListener {
            Toast.makeText(this, "try to exit!", Toast.LENGTH_LONG).show()
        }

        deckButton.setOnClickListener {
            startActivity(Intent(this, MyDeckActivity::class.java))
        }

        developers.setOnClickListener {
            startActivity(Intent(this, DevelopersActivity::class.java))
        }

        materialCardView.setOnClickListener {
            startActivity(Intent(this, MyTeamActivity::class.java))
        }
    }

    private fun toolBarItems(user: User): User {
        val userImage = findViewById<ImageView>(R.id.img_userIcon_main)
        val userName = findViewById<TextView>(R.id.txt_userName_main)

        Picasso.get().load(user.imageUrl).into(userImage)
        userName.text = user.nickName

        userImage.setOnClickListener {
            newUserFragment(user)
        }

        return user
    }

    private fun getAllCardsFromDB(user: User) {

        val cardManager = mutableListOf<Hero>()

        databaseViewModel.getAllCards().observe(this) { cardlist ->
            val _cardList = cardlist as List<CardEntity>
            _cardList.forEach {
                cardManager.add(
                    Hero(
                        it.id,
                        it.heroName,
                        it.name,
                        it.imageUrl,
                        it.durability,
                        it.energy,
                        it.fightingSkills,
                        it.inteligence,
                        it.speed,
                        it.strength,
                        it.description
                    )
                )
            }

            NEW_USER.setUser(user)
            NEW_USER.addOnDeck(cardManager)
            showTeamCards(NEW_USER.getTeam())

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
            var getStars = ""
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
        frame: Int,
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

}