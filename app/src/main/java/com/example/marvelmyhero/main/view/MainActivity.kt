package com.example.marvelmyhero.main.view

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.marvelmyhero.R
import com.example.marvelmyhero.card.model.CardFirebase
import com.example.marvelmyhero.card.model.Hero
import com.example.marvelmyhero.card.view.MiniCardFragment
import com.example.marvelmyhero.db.database.AppDataBase
import com.example.marvelmyhero.db.entity.CardEntity
import com.example.marvelmyhero.db.repository.CardRepository
import com.example.marvelmyhero.db.viewmodel.CardViewModel
import com.example.marvelmyhero.deck.view.MyDeckActivity
import com.example.marvelmyhero.developers.view.DevelopersActivity
import com.example.marvelmyhero.login.model.User
import com.example.marvelmyhero.team.view.MyTeamActivity
import com.example.marvelmyhero.utils.AlertManager
import com.example.marvelmyhero.utils.CardManager
import com.example.marvelmyhero.utils.UserCardUtils.Companion.NEW_USER
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    data class DatabaseUser(
        val name: String = "",
        val nickname: String = "",
        val imageUrl: String = "",
        val deck: MutableList<CardFirebase> = mutableListOf(),
        val team: MutableList<CardFirebase> = mutableListOf(),
    )

    val exitButton: ImageView by lazy { findViewById(R.id.ic_exit_main) }

    val deckButton: MaterialButton by lazy { findViewById(R.id.btn_myDeck_main) }

    val materialCardView: MaterialCardView by lazy { findViewById(R.id.materialCardView_main) }

    val developers: ImageView by lazy { findViewById(R.id.img_developers) }

    val userImage: ImageView by lazy { findViewById(R.id.img_userIcon_main) }

    val userName: TextView by lazy { findViewById(R.id.txt_userName_main) }

    private lateinit var databaseViewModel: CardViewModel

    private lateinit var user: User

    private var cardAlert = AlertManager(this)

    private val cardManager = CardManager()

    private val firebaseUser = FirebaseAuth.getInstance().currentUser

    private val firebaseDatabase = FirebaseDatabase.getInstance()

    private var myRef = firebaseDatabase.getReference(firebaseUser?.uid.toString())

//    private lateinit var databaseUser: DatabaseUser

    override fun onStart() {
        super.onStart()

//        myRef.child("deck").addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                val value = dataSnapshot.getValue()
//                Toast.makeText(this@MainActivity, value?.toString(), Toast.LENGTH_LONG).show()
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                // Failed to read value
//            }
//        })

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        user = toolBarItems(User("", "", ""))

        databaseViewModel = ViewModelProvider(
            this,
            CardViewModel.CardViewModelFactory(
                CardRepository(
                    AppDataBase.getDatabase(this).cardDao()
                )
            )
        ).get(CardViewModel::class.java)



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


        Picasso.get().load(user.imageUrl).into(userImage)
        userName.text = user.nickName

        userImage.setOnClickListener {
            newUserFragment(user)
        }

        return user
    }

    private fun getAllCardsFromDB(user: User) {

        val cardList = mutableListOf<Hero>()

        databaseViewModel.getAllCards().observe(this) { cardlist ->
            val _cardList = cardlist as List<CardEntity>
            _cardList.forEach {
                cardList.add(
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

//          Deve mostrar somente a primeira vez que o usuário logar.. a segunda deve adicionar novos cards ao Deck
//            cardAlert.showNewCard(user.deck)
//
            NEW_USER.setUser(user)
            NEW_USER.addOnDeck(mutableListOf(cardList[0], cardList[1], cardList[2]))
            showTeamCards(mutableListOf(cardList[0], cardList[1], cardList[2]))

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