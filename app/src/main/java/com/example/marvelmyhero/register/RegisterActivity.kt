package com.example.marvelmyhero.register
import android.content.Intent
import android.graphics.Color.alpha
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
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
import com.example.marvelmyhero.login.view.LoginFragment
import com.example.marvelmyhero.main.view.MainActivity
import com.example.marvelmyhero.utils.CardManager
import com.example.marvelmyhero.utils.Constants
import com.example.marvelmyhero.utils.Constants.CONTEXT_RESQUEST_CODE
import com.example.marvelmyhero.utils.Constants.HANDLER_TIME_BRIDGE
import com.example.marvelmyhero.utils.Constants.HANDLER_TIME_BRIDGE_2
import com.example.marvelmyhero.utils.Constants.IMAGE
import com.example.marvelmyhero.utils.Constants.IS_NEW_USER
import com.example.marvelmyhero.utils.Constants.NAME
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import pl.droidsonroids.gif.GifImageView
class RegisterActivity : AppCompatActivity() {
    data class DatabaseUser(
        val name: String = "",
        val nickName: String = "",
        val imageUrl: String = "",
        val deck: MutableList<MainActivity.DatabaseCard>? = null,
        val team: MutableList<MainActivity.DatabaseCard>? = null,
    )
    private var imageUri: Uri? = null
    private val userImage: ImageView by lazy { findViewById(R.id.image_userImage_register) }
    private val nickname: TextInputEditText by lazy { findViewById(R.id.editText_nickName_register) }
    private val name: TextInputEditText by lazy { findViewById(R.id.editText_name_register) }
    private val submitButton: Button by lazy { findViewById(R.id.btn_submit_register) }
    private val changeImageButton: ImageView by lazy { findViewById(R.id.image_editIcon_register) }
    private val cardManager = CardManager()
    private lateinit var databaseViewModel: CardViewModel
    private val materialCardView by lazy { findViewById<MaterialCardView>(R.id.materialCardView_register) }
    private val progressBar by lazy { findViewById<GifImageView>(R.id.circle_image) }
    //    Firebase
    private val firebaseUser = FirebaseAuth.getInstance().currentUser
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val storageRef = FirebaseStorage.getInstance().getReference(firebaseUser?.uid.toString())
    private var myRef = firebaseDatabase.getReference(firebaseUser?.uid.toString())
    override fun onCreate(savedInstanceState: Bundle?) {
        isNewUser()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        Handler(Looper.getMainLooper()).postDelayed({
            materialCardView.animate().apply {
                duration = 500
                alpha(1f)
            }
            progressBar.animate().apply {
                duration = 500
                alpha(0f)
            }
        }, HANDLER_TIME_BRIDGE)
        var intentName = intent.getStringExtra(NAME)!!
        if (intentName.equals("null")){
            intentName = ""
        }
        name.setText(intentName)
        databaseViewModel = ViewModelProvider(
            this,
            CardViewModel.CardViewModelFactory(
                CardRepository(
                    AppDataBase.getDatabase(this).cardDao()
                )
            )
        ).get(CardViewModel::class.java)
        submitButton.setOnClickListener {
            if (nickname.text.isNullOrEmpty()) {
                nickname.error = getString(R.string.nickName_error)
            }
            if (name.text.isNullOrEmpty()) {
                name.error = getString(R.string.name_error)
            }
            if (!name.text.isNullOrEmpty() && !nickname.text.isNullOrEmpty()) {
                setUser()
            }
        }
        changeImageButton.setOnClickListener {
            findFile()
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CONTEXT_RESQUEST_CODE && resultCode == RESULT_OK) {
            imageUri = data?.data
            userImage.setImageURI(imageUri)
            sendImage()
        }
    }
    private fun setUser() {
        getAllCardsFromDB()
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Toast.makeText(this@RegisterActivity, "Submit", Toast.LENGTH_LONG).show()
                val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                intent.putExtra(IMAGE, imageUri.toString())
                startActivity(intent)
                finish()
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@RegisterActivity,
                    "Error :(  Try again latter",
                    Toast.LENGTH_LONG).show()
            }
        })
    }
    private fun sendImage() {
        imageUri?.run {
            val extension = MimeTypeMap.getSingleton()
                .getExtensionFromMimeType(contentResolver.getType(imageUri!!))
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
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, CONTEXT_RESQUEST_CODE)
    }
    private fun getAllCardsFromDB() {
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
            val randomFirebaseCards = addOnDeck(randomCards)
            val randomFirebaseCardsTeam =
                addOnDeck(mutableListOf(randomCards[0], randomCards[1], randomCards[2]))
            myRef.setValue(User(nickname.text.toString(),
                name.text.toString(),
                firebaseUser?.uid.toString()))
            myRef.child("deck").setValue(randomFirebaseCards)
            myRef.child("team").setValue(randomFirebaseCardsTeam)
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
    private fun isNewUser() {
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(DatabaseUser::class.java)
                IS_NEW_USER = value == null
                storageRef.downloadUrl.addOnSuccessListener {
                    imageUri = it
                    if (!IS_NEW_USER){
                        Handler(Looper.getMainLooper()).postDelayed({
                            val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                            intent.putExtra(IMAGE, imageUri.toString())
                            startActivity(intent)
                            finish()
                        }, HANDLER_TIME_BRIDGE_2)
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}