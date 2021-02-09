package com.example.marvelmyhero.verifications


import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.marvelmyhero.R
import com.example.marvelmyhero.card.model.Hero
import com.example.marvelmyhero.db.database.AppDataBase
import com.example.marvelmyhero.db.entity.CardEntity
import com.example.marvelmyhero.db.repository.CardRepository
import com.example.marvelmyhero.db.viewmodel.CardViewModel
import com.example.marvelmyhero.login.model.User
import com.example.marvelmyhero.main.view.MainActivity
import com.example.marvelmyhero.register.RegisterActivity
import com.example.marvelmyhero.utils.Constants
import com.example.marvelmyhero.utils.Constants.isAble
import com.example.marvelmyhero.utils.UserVariables.IS_MY_FIRST_TIME_ON_APP
import com.example.marvelmyhero.utils.UserVariables.MY_USER
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class VerificationsActivity : AppCompatActivity() {

    private var imageUri: Uri? = null
    private val firebaseUser = FirebaseAuth.getInstance().currentUser
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val storageRef = FirebaseStorage.getInstance().getReference(firebaseUser?.uid.toString())
    private var myRef = firebaseDatabase.getReference(firebaseUser?.uid.toString())

    data class DatabaseCard(
        val favorite: Boolean = false,
        val id: Int = 0,
    )

    data class DatabaseUser(
        val nickName: String = "",
        val imageUrl: String = "",
        val deck: MutableList<DatabaseCard>? = null
    )

    private lateinit var databaseViewModel: CardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verifications)
        Log.d("USER_FLUX", "-> verification")

        initDBViewModel()
        isNewUser()

    }

    private fun initDBViewModel() {
        databaseViewModel = ViewModelProvider(
            this,
            CardViewModel.CardViewModelFactory(
                CardRepository(
                    AppDataBase.getDatabase(this).cardDao()
                )
            )
        ).get(CardViewModel::class.java)
    }


    private fun isNewUser() {

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(DatabaseUser::class.java)

                if (value != null && isAble) {

                    Log.d("USER_FLUX", "-> usuario firebase -> $MY_USER")
                    checkForNullOptionsWithFireBase(value)

                    storageRef.downloadUrl.addOnSuccessListener {
                        Log.d("USER_FLUX", "-> imageUri $imageUri")
                        MY_USER!!.imageUrl = it.toString()
                    }

                    Log.d("USER_FLUX", "-> control deck ${MY_USER!!.deck}")
                    handlerToMainActivity()

                }else if(isAble){
                    Log.d("USER_FLUX", "-> novo usuario -> $MY_USER")
                    if (MY_USER == null){
                        MY_USER = User(
                            firebaseUser?.displayName.toString(),
                            firebaseUser?.uid.toString()
                        )
                    }
                    Log.d("USER_FLUX", "-> is first time on app (verification)? $IS_MY_FIRST_TIME_ON_APP")
                    handlerToRegisterActivity()
                } else if (!isAble){
                    finish()
                }
            }
            override fun onCancelled(error: DatabaseError) { }
        })

    }

    private fun handlerToRegisterActivity() {
        IS_MY_FIRST_TIME_ON_APP = true
        Handler(Looper.getMainLooper()).postDelayed({
            Log.d("USER_FLUX", "-> registeractivity")
            startActivity(Intent(this@VerificationsActivity, RegisterActivity::class.java))
            finish()
        }, Constants.HANDLER_TIME_BRIDGE_2)
    }

    private fun handlerToMainActivity() {
        Handler(Looper.getMainLooper()).postDelayed({
            Log.d("USER_FLUX", "-> mainactivity")
            startActivity(Intent(this@VerificationsActivity, MainActivity::class.java))
            IS_MY_FIRST_TIME_ON_APP = false
            Log.d("USER_FLUX", "-> is first time on app (verification)? $IS_MY_FIRST_TIME_ON_APP")
            finish()
        }, Constants.HANDLER_TIME_BRIDGE_2)
    }

    private fun checkForNullOptionsWithFireBase(value: DatabaseUser) {
        if (MY_USER == null) {
            MY_USER = User(
                value.nickName,
                value.imageUrl
            )
        }
        if (MY_USER?.deck.isNullOrEmpty()) {
            getDeckFromDb(value.deck)
        }
    }

    private fun getDeckFromDb(deck: MutableList<DatabaseCard>?) {

        Log.d("USER_FLUX", "-> sincronizando cards com banco de dados")

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
            getDeck(deck, cardList)
        }
    }

    private fun getDeck(
        myDeck: MutableList<DatabaseCard>?,
        cardList: MutableList<Hero>,
    ){
        Log.d("USER_FLUX", "-> inserindo cards ao Deck")

        val deck = mutableListOf<Hero>()

        myDeck?.forEach { databaseCard ->
            cardList.forEach { hero ->
                if (hero.id == databaseCard.id) {
                    hero.favorite = databaseCard.favorite
                    deck.add(hero)
                }
            }
        }
        MY_USER!!.deck.addAll(deck)

    }
}