package com.example.marvelmyhero.main.view

import android.content.*
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
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
import com.example.marvelmyhero.login.view.LoginActivity
import com.example.marvelmyhero.team.view.MyTeamActivity
import com.example.marvelmyhero.utils.AlertManager
import com.example.marvelmyhero.utils.CardManager
import com.example.marvelmyhero.utils.Constants.isAble
import com.example.marvelmyhero.utils.UserVariables.IS_MY_FIRST_TIME_ON_APP
import com.example.marvelmyhero.utils.UserVariables.MY_USER
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import java.io.ByteArrayOutputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    data class DatabaseCard(
        val favorite: Boolean = false,
        val id: Int = 0,
    )

    data class DatabaseUser(
        val nickName: String = "",
        val imageUrl: String = "",
        val deck: MutableList<DatabaseCard>? = null
    )

    private val exitButton: ImageView by lazy { findViewById(R.id.ic_exit_main) }
    private val deckButton: MaterialButton by lazy { findViewById(R.id.btn_myDeck_main) }
    private val materialCardView: MaterialCardView by lazy { findViewById(R.id.materialCardView_main) }
    private val developers: ImageView by lazy { findViewById(R.id.img_developers) }
    private val userImage: ImageView by lazy { findViewById(R.id.img_userIcon_main) }
    private val userName: TextView by lazy { findViewById(R.id.txt_userName_main) }
    private val cardViewMain by lazy { findViewById<MaterialCardView>(R.id.materialCardView_main) }
    private val shareButton by lazy { findViewById<MaterialButton>(R.id.btn_share_main) }
    private lateinit var databaseViewModel: CardViewModel
    private var imageUri: Uri? = null
    private var cardAlert = AlertManager(this)
    private val cardManager = CardManager()

    //    Firebase
    private val firebaseUser = FirebaseAuth.getInstance().currentUser
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val storageRef =
        FirebaseStorage.getInstance().getReference(firebaseUser?.uid.toString())
    private var myRef = firebaseDatabase.getReference(firebaseUser?.uid.toString())

    lateinit var today: String
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val calendar: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        isAble = false
        Log.d("USER_FLUX", "-> MaiActivity")
        initDbViewModel()

        validateDay()

        toolBarItems(MY_USER!!)

        Log.d("USER_FLUX", "-> first time ? $IS_MY_FIRST_TIME_ON_APP")
        if (IS_MY_FIRST_TIME_ON_APP) {
            cardAlert.newCardAlert(cardManager, MY_USER!!.deck, false)
        }

        exitButton.setOnClickListener {
            exitDialog()
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

        shareButton.setOnClickListener {
            share()
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("USER_FLUX", "-> onResume()")
        val team = mutableListOf(MY_USER!!.deck[0], MY_USER!!.deck[1], MY_USER!!.deck[2])
        showTeamCards(team)
        Log.d("USER_FLUX", "-> update deck")
        myRef.child("deck").setValue(transformToFirebaseFormat(MY_USER!!.deck))
    }

    private fun transformToFirebaseFormat(list: MutableList<Hero>): MutableList<CardFirebase> {
        val cardFirebase = mutableListOf<CardFirebase>()
        list.forEach {
            cardFirebase.add(
                CardFirebase(
                    it.id,
                    it.favorite
                )
            )
        }
        return cardFirebase
    }

    private fun initDbViewModel() {
        databaseViewModel = ViewModelProvider(
            this,
            CardViewModel.CardViewModelFactory(
                CardRepository(
                    AppDataBase.getDatabase(this).cardDao()
                )
            )
        ).get(CardViewModel::class.java)
    }

    private fun toolBarItems(user: User) {

        Picasso.get().load(user.imageUrl).into(userImage)
        userName.text = user.nickName

        userImage.setOnClickListener {
            newUserFragment(user)
        }
    }

    private fun showTeamCards(team: MutableList<Hero>) {

        Log.d("USER_FLUX", "-> showTeamCards()")

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

    private fun share() {
        val bitmap = getBitmapFromView(cardViewMain)

        val icon: Bitmap = bitmap!!
        val share = Intent(Intent.ACTION_SEND)
        share.type = "image/jpeg"


        try {
            val pm = packageManager
            val bytes = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
            val path = MediaStore.Images.Media.insertImage(
                this.contentResolver,
                bitmap,
                "qualquer coisa",
                null
            )
            val imageUri = Uri.parse(path)


            val outstream: OutputStream?
            outstream = contentResolver.openOutputStream(imageUri!!)
            icon.compress(Bitmap.CompressFormat.JPEG, 100, outstream)
            outstream?.close()
            share.putExtra(Intent.EXTRA_STREAM, imageUri)
            startActivity(Intent.createChooser(share, "Share Image"))
        } catch (e: Exception) {
            System.err.println(e.toString())
        }
    }

    open fun getBitmapFromView(view: View): Bitmap? {
        var bitmap =
            Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        var canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
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

    private fun exitDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.exitDialog_title))
            .setMessage(getString(R.string.exitDialog_message))
            .setNegativeButton(getString(R.string.exitDialog_negativeButton)) { _, _ ->
                closeContextMenu()
            }
            .setPositiveButton(getString(R.string.exitDialog_positiveButton)) { _, _ ->
                MY_USER = null
                isAble = true
                Firebase.auth.signOut()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            .show()
    }

    private fun validateDay(){
        var dayComparePreference = getSharedPreferences(DATE_PREFS, MODE_PRIVATE)
        today = dayComparePreference.getString(KEEP_CONNECTED_PREFS, "").toString()
        val isDay = dateFormat.format(calendar.time)

        if (!today.isNullOrEmpty()) {
            if (isDay.compareTo(today) == 1) {
                dayComparePreference.edit()
                    .putString(KEEP_CONNECTED_PREFS, isDay)
                    .apply()
                getDeckFromDb()
            }
        } else {
            dayComparePreference.edit()
                .putString(KEEP_CONNECTED_PREFS, isDay)
                .apply()
        }
    }

    private fun getDeckFromDb() {

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


            val randomCards = CardManager().random3(cardList)

            showRandom3Cards(randomCards)

            MY_USER!!.deck.addAll(CardManager().random3(cardList))
        }
    }

    fun showRandom3Cards(randomList: MutableList<Hero>){

        MaterialAlertDialogBuilder(this)
            .setTitle("Welcome Back")
            .setMessage("You won a 3 new cards, would you like to see them?")
            .setPositiveButton("Yes") { _, _ ->
                showNewCard(randomList)
            }
            .setNegativeButton("No") { _, _ ->
                closeContextMenu()
            }
            .show()

    }

    private fun showNewCard(cardList: MutableList<Hero>) {

        cardList.forEach {
            var getStars = ""
            val starValue = getString(R.string.classificationStar)

            when (it.classification) {
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

                .setMessage("Name : ${it.heroName} \n Classification: $getStars ")
                .setPositiveButton("OK") { _, _ ->
                    closeContextMenu()
                }
                .show()
        }
    }
    companion object {
        const val DATE_PREFS = "DATE"
        const val KEEP_CONNECTED_PREFS = "GET_DATE_FIRST_LOGIN"
    }
}