package com.example.marvelmyhero.splash.view
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.marvelmyhero.R
import com.example.marvelmyhero.card.model.Hero
import com.example.marvelmyhero.data.repository.CharacterRepository
import com.example.marvelmyhero.db.database.AppDataBase
import com.example.marvelmyhero.db.entity.CardEntity
import com.example.marvelmyhero.db.repository.CardRepository
import com.example.marvelmyhero.db.viewmodel.CardViewModel
import com.example.marvelmyhero.login.model.User
import com.example.marvelmyhero.login.view.LoginActivity
import com.example.marvelmyhero.splash.viewmodel.CharacterViewModel
import com.example.marvelmyhero.utils.CardManager
import com.example.marvelmyhero.utils.Constants.HANDLER_TIME
import com.example.marvelmyhero.utils.Constants.HANDLER_TIME_ANIMATION
import com.example.marvelmyhero.utils.Constants.HANDLER_TIME_ANIMATION_PROGRESS_BAR
import com.example.marvelmyhero.utils.UserVariables.IS_MY_FIRST_TIME_ON_APP
import com.example.marvelmyhero.verifications.VerificationsActivity
import com.example.marvelmyhero.utils.UserVariables.MY_USER
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import pl.droidsonroids.gif.GifImageView

@Suppress("COMPATIBILITY_WARNING")
class SplashScreen : AppCompatActivity() {

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
    private lateinit var viewModel: CharacterViewModel
    private val cardManager = CardManager()
    private val progressBar: ProgressBar by lazy { findViewById<ProgressBar>(R.id.progressBar_splash) }

    private val firebaseUser = FirebaseAuth.getInstance().currentUser
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private var myRef = firebaseDatabase.getReference(firebaseUser?.uid.toString())
    private val storageRef =
        FirebaseStorage.getInstance().getReference(firebaseUser?.uid.toString())
    private var isCurrentUser = false
    private var imageUri: Uri? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        databaseViewModel = ViewModelProvider(
            this,
            CardViewModel.CardViewModelFactory(
                CardRepository(
                    AppDataBase.getDatabase(this).cardDao()
                )
            )
        ).get(CardViewModel::class.java)
        viewModel = ViewModelProvider(
            this,
            CharacterViewModel.CharacterViewModelFactory(CharacterRepository())
        ).get(CharacterViewModel::class.java)
        dataBaseCheck(cardManager)
        animation()
    }
    private fun dataBaseCheck(cardManager: CardManager) {
        val size = cardManager.getIdsList()
        databaseViewModel.count().observe(this) {
            val count = it.toString().toInt()
            if (count == size.size) {

                Log.d("USER_FLUX", "-> pegando cards do banco de dados")
                Handler(Looper.getMainLooper()).postDelayed({
                    firebaseVerification()
                }, HANDLER_TIME)
            } else {
                Log.d("USER_FLUX", "-> baixando dados da api")
                getApiCharacters(cardManager)
            }
        }
    }
    private fun getApiCharacters(cardManager: CardManager) {
        val allCharId = cardManager.getIdsList()
        viewModel.getCharacter(allCharId).observe(this) {
            cardManager.addCardsOnManager(it)
            createDatabase(cardManager.getCardList())

            var validator = false
            do {
                val cardsListCount = cardManager.getCardList().size
                val idsListCount = cardManager.getIdsList().size
                if (cardsListCount == idsListCount) {
                    validator = true
                }
            } while (!validator)
            firebaseVerification()
        }
    }
    private fun createDatabase(cardList: List<CardEntity>) {
        cardList.forEach {
            databaseViewModel.addCard(it).observe(this) { isAdd ->
                Log.i("DATA_BASE", "Insert item: $isAdd")
            }
        }
    }
    private fun animation() {
        val gifSplash = findViewById<GifImageView>(R.id.gif_marvel)
        val logoSplash = findViewById<ImageView>(R.id.img_splash_screen)
        Handler(Looper.getMainLooper()).postDelayed({
            gifSplash.animate().apply {
                duration = 3000
                alpha(0f)
            }
            logoSplash.animate().apply {
                duration = 3000
                alpha(1f)
                scaleX(0.80f)
                scaleY(0.80f)
            }
            Handler(Looper.getMainLooper()).postDelayed({
                progressBar.animate().apply {
                    duration = 3000
                    alpha(1f)
                }
            }, HANDLER_TIME_ANIMATION_PROGRESS_BAR)
        }, HANDLER_TIME_ANIMATION)
    }
    private fun firebaseVerification() {
        Log.d("USER_FLUX", "-> firebaseVerification()")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(DatabaseUser::class.java)
                IS_MY_FIRST_TIME_ON_APP = value == null
                Log.d("USER_FLUX", "-> is my first? $IS_MY_FIRST_TIME_ON_APP")
                if (!IS_MY_FIRST_TIME_ON_APP) {
                    storageRef.downloadUrl.addOnSuccessListener {
                        imageUri = it
                        Log.d("USER_FLUX", "-> imageUri $imageUri")
                        MY_USER = User(
                            value?.nickName.toString(),
                            imageUri.toString()
                        )
                        getDeckFromDb(value!!.deck)
                    }
                }else {
                    Log.d("USER_FLUX", "-> sem usuário cadastrado")
                    callNextPage()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@SplashScreen, "ERROR: INTERNET", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun callNextPage() {
        Log.d("USER_FLUX", "-> callNextPage()")

        val firebaseUser = FirebaseAuth.getInstance().currentUser

        if (firebaseUser != null) {
            Log.d("USER_FLUX", "-> firebaseUser -> VerificationsActivity")
            val intent = Intent(this, VerificationsActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Log.d("USER_FLUX", "-> sem firebaseUser -> LoginActivity")
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
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


        callNextPage()
    }


}