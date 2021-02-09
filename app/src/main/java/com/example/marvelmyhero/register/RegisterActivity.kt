package com.example.marvelmyhero.register
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.marvelmyhero.R
import com.example.marvelmyhero.card.model.CardFirebase
import com.example.marvelmyhero.card.model.Hero
import com.example.marvelmyhero.db.database.AppDataBase
import com.example.marvelmyhero.db.entity.CardEntity
import com.example.marvelmyhero.db.repository.CardRepository
import com.example.marvelmyhero.db.viewmodel.CardViewModel
import com.example.marvelmyhero.login.model.User
import com.example.marvelmyhero.main.view.MainActivity
import com.example.marvelmyhero.utils.CardManager
import com.example.marvelmyhero.utils.Constants.CONTEXT_RESQUEST_CODE
import com.example.marvelmyhero.utils.Constants.IMAGE
import com.example.marvelmyhero.utils.Constants.NAME
import com.example.marvelmyhero.utils.UserVariables.IS_MY_FIRST_TIME_ON_APP
import com.example.marvelmyhero.utils.UserVariables.MY_USER
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage

class RegisterActivity : AppCompatActivity() {
    data class DatabaseCard(
        val favorite: Boolean = false,
        val id: Int = 0,
    )

    data class DatabaseUser(
        val nickName: String = "",
        val imageUrl: String = "",
        val deck: MutableList<DatabaseCard>? = null
    )

    private var imageUri: Uri? = null
    private val userImage: ImageView by lazy { findViewById<ImageView>(R.id.image_userImage_register) }
    private val nickname: TextInputEditText by lazy { findViewById<TextInputEditText>(R.id.editText_nickName_register) }
    private val submitButton: Button by lazy { findViewById<Button>(R.id.btn_submit_register) }
    private val changeImageButton: ImageView by lazy { findViewById<ImageView>(R.id.image_editIcon_register) }
    private val cardManager = CardManager()
    private lateinit var databaseViewModel: CardViewModel

    //    Firebase
    private val firebaseUser = FirebaseAuth.getInstance().currentUser
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val storageRef = FirebaseStorage.getInstance().getReference(firebaseUser?.uid.toString())
    private var myRef = firebaseDatabase.getReference(firebaseUser?.uid.toString())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        Log.d("USER_FLUX", "-> RegisterActivity")
        IS_MY_FIRST_TIME_ON_APP = true

//        var intentName = intent.getStringExtra(NAME)!!
//        if (intentName.equals("null")){
//            intentName = ""
//        }
//
//        nickname.setText(intentName)

        initDBViewModel()

        submitButton.setOnClickListener {
            Log.d("USER_FLUX", "-> submit button")
            if (nickname.text.isNullOrEmpty()) {
                nickname.error = getString(R.string.nickName_error)
            }
            else getAllCardsFromDB()
        }
        changeImageButton.setOnClickListener {
            Log.d("USER_FLUX", "-> changeImageButton")
            findFile()
        }
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("USER_FLUX", "-> onActivityResult()")
        if (requestCode == CONTEXT_RESQUEST_CODE && resultCode == RESULT_OK) {
            imageUri = data?.data
            userImage.setImageURI(imageUri)
        }
    }
//    private fun setUser() {
//
//        myRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                Log.d("CARDS_FROM_DB","Cards carregados")
//                val intent = Intent(this@RegisterActivity, MainActivity::class.java)
//                intent.putExtra(IMAGE, imageUri.toString())
//                startActivity(intent)
//                finish()
//            }
//            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText(this@RegisterActivity,
//                    "Error :(  Try again latter",
//                    Toast.LENGTH_LONG).show()
//            }
//        })
//    }
    private fun sendImage() {
    Log.d("USER_FLUX", "-> sendImage()")
        if (imageUri == null && firebaseUser?.photoUrl != null) {
            imageUri = Uri.parse(firebaseUser.photoUrl.toString())
        }else if (imageUri == null && firebaseUser?.photoUrl == null){
            imageUri = Uri.parse("https://cdn.dribbble.com/users/1063314/screenshots/3229288/stanlee.png")
        }
    MY_USER!!.imageUrl = imageUri.toString()

        Log.d("USER_FLUX", "-> imageUri $imageUri")

        imageUri!!.run {
            storageRef.putFile(this)
                .addOnSuccessListener {
                    Log.d("FIREBASE_PIC", storageRef.toString())
                }
                .addOnFailureListener {
                    Toast.makeText(this@RegisterActivity,
                        "ERROR: Upload Picture!!",
                        Toast.LENGTH_LONG).show()
                }
        }
    }
    private fun findFile() {
        Log.d("USER_FLUX", "-> findFile()")
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, CONTEXT_RESQUEST_CODE)
    }
    private fun getAllCardsFromDB() {
        Log.d("USER_FLUX", "-> getAllCardsFromDB()")
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
            val randomCards = cardManager.random5(cardList)
            MY_USER!!.deck.addAll(randomCards)
            Log.d("USER_FLUX", "-> deck ${MY_USER!!.deck}")



            val randomFirebaseCards = addOnDeck(randomCards)

            Log.d("USER_FLUX", "-> armazenando nome")
            MY_USER!!.nickName = nickname.text.toString()
            myRef.setValue(User(
                nickname.text.toString(),
                firebaseUser!!.uid
            ))

            Log.d("USER_FLUX", "-> armazenando cards")
            myRef.child("deck").setValue(randomFirebaseCards)
            sendImage()

            Log.d("USER_FLUX", "-> mainactivity")
            startActivity(Intent(this, MainActivity::class.java))
            finish()

        }
    }
    private fun addOnDeck(list: MutableList<Hero>): MutableList<CardFirebase> {
        val cardFirebase = mutableListOf<CardFirebase>()
        list.forEach {
            cardFirebase.add(CardFirebase(
                it.id,
                it.favorite
            ))
        }
        return cardFirebase
    }
}