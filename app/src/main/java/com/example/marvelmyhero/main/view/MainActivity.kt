package com.example.marvelmyhero.main.view


import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.*
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.marvelmyhero.R
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
import com.example.marvelmyhero.utils.Constants.CURRENT_USER
import com.example.marvelmyhero.utils.Constants.IMAGE
import com.example.marvelmyhero.utils.Constants.IS_NEW_USER
import com.example.marvelmyhero.utils.UserCardUtils.Companion.NEW_USER
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import java.io.ByteArrayOutputStream
import java.io.OutputStream

class MainActivity : AppCompatActivity() {

    data class DatabaseCard(
        val favorite: Boolean = false,
        val id: Int = 0,
    )

    data class DatabaseUser(
        val name: String = "",
        val nickName: String = "",
        val imageUrl: String = "",
        val deck: MutableList<DatabaseCard>? = null,
        val team: MutableList<DatabaseCard>? = null,
    )

    private val exitButton: ImageView by lazy { findViewById<ImageView>(R.id.ic_exit_main) }
    private val deckButton: MaterialButton by lazy { findViewById<MaterialButton>(R.id.btn_myDeck_main) }
    private val materialCardView: MaterialCardView by lazy { findViewById<MaterialCardView>(R.id.materialCardView_main) }
    private val developers: ImageView by lazy { findViewById<ImageView>(R.id.img_developers) }
    private val userImage: ImageView by lazy { findViewById<ImageView>(R.id.img_userIcon_main) }
    private val userName: TextView by lazy { findViewById<TextView>(R.id.txt_userName_main) }
    private val cardViewMain by lazy { findViewById<MaterialCardView>(R.id.materialCardView_main) }
    private val shareButton by lazy { findViewById<MaterialButton>(R.id.btn_share_main) }
    private lateinit var databaseViewModel: CardViewModel
    private var imageUri: Uri? = null
    private var user = User("", "", "")
    private var cardAlert = AlertManager(this)
    private val cardManager = CardManager()
    private val myDeck: MutableList<DatabaseCard> = mutableListOf()
    private val myTeam: MutableList<DatabaseCard> = mutableListOf()


    //    Firebase
    private val firebaseUser = FirebaseAuth.getInstance().currentUser
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val storageRef =
        FirebaseStorage.getInstance().getReference(firebaseUser?.uid.toString())
    private var myRef = firebaseDatabase.getReference(firebaseUser?.uid.toString())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        storageRef.downloadUrl.addOnSuccessListener {
            imageUri = it
        }

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(DatabaseUser::class.java)

                user.nickName = value?.nickName.toString()
                user.name = value?.name.toString()

                value?.deck?.forEach {
                    myDeck.add(it)
                }
                value?.team?.forEach {
                    myTeam.add(it)
                }

                if (imageUri == null) {
                    user.imageUrl = intent.getStringExtra(IMAGE).toString()
                } else {
                    user.imageUrl = imageUri.toString()
                }
                NEW_USER.setUser(user)
                toolBarItems(user)


                getAllCardsFromDB(user, myDeck, myTeam)

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, "ERROR: INTERNET", Toast.LENGTH_LONG).show()
            }
        })

        databaseViewModel = ViewModelProvider(
            this,
            CardViewModel.CardViewModelFactory(
                CardRepository(
                    AppDataBase.getDatabase(this).cardDao()
                )
            )
        ).get(CardViewModel::class.java)

        exitButton.setOnClickListener {
            exitDialog()
        }

        deckButton.setOnClickListener {
            startActivityForResult(Intent(this, MyDeckActivity::class.java), 15)
        }

        developers.setOnClickListener {
            startActivity(Intent(this, DevelopersActivity::class.java))
        }

        checkPermissionREAD_EXTERNAL_STORAGE(this)

        materialCardView.setOnClickListener {
            startActivity(Intent(this, MyTeamActivity::class.java))
        }

        shareButton.setOnClickListener {
            share()
        }
    }

    override fun onRestart() {
        super.onRestart()
        showTeamCards(CURRENT_USER.team)
    }

    val MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123

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

    fun checkPermissionREAD_EXTERNAL_STORAGE(
        context: Context?
    ): Boolean {
        val currentAPIVersion = Build.VERSION.SDK_INT
        return if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) !== PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                ) {
                    showDialog(
                        "External storage", context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                } else {
                    ActivityCompat
                        .requestPermissions(
                            this,
                            arrayOf<String>(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                            MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE
                        )
                }
                false
            } else {
                true
            }
        } else {
            true
        }
    }

    fun showDialog(
        msg: String, context: Context?,
        permission: String
    ) {
        val alertBuilder: AlertDialog.Builder = AlertDialog.Builder(context)
        alertBuilder.setCancelable(true)
        alertBuilder.setTitle("Permission necessary")
        alertBuilder.setMessage("$msg permission is necessary")
        alertBuilder.setPositiveButton(android.R.string.yes,
            object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    ActivityCompat.requestPermissions(
                        (context as Activity?)!!, arrayOf(permission),
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE
                    )
                }

            })
        val alert: AlertDialog = alertBuilder.create()
        alert.show()
    }

    private fun toolBarItems(user: User): User {

        Picasso.get().load(user.imageUrl).into(userImage)
        userName.text = user.nickName

        userImage.setOnClickListener {
            newUserFragment(user)
        }
        return user
    }

    private fun getAllCardsFromDB(
        user: User,
        myDeck: MutableList<DatabaseCard>,
        myTeam: MutableList<DatabaseCard>,
    ) {

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
            CURRENT_USER.deck

            if (CURRENT_USER.deck.size == 0){
                val deck = getDeck(myDeck, cardList)
                val team = getTeam(myTeam, deck)
                val index_01 = deck.indexOf(team[0])
                deck.removeAt(index_01)
                val index_02 = deck.indexOf(team[1])
                deck.removeAt(index_02)
                val index_03 = deck.indexOf(team[2])
                deck.removeAt(index_03)


                CURRENT_USER.deck = deck
                CURRENT_USER.team = team

                if (IS_NEW_USER) {
                    cardAlert.newCardAlert(cardManager, deck, false)
                }
            }



            showTeamCards(CURRENT_USER.team)
        }
    }

    private fun getTeam(
        myTeam: MutableList<DatabaseCard>,
        deck: MutableList<Hero>,
    ): MutableList<Hero> {

        val team = mutableListOf<Hero>()

        myTeam.forEach {
            deck.forEach { hero ->
                if (it.id == hero.id) {
                    team.add(hero)
                }
            }
        }

        return team
    }

    private fun getDeck(
        myDeck: MutableList<DatabaseCard>,
        cardList: MutableList<Hero>,
    ): MutableList<Hero> {

        val deck = mutableListOf<Hero>()

        myDeck.forEach { databaseCard ->
            cardList.forEach { hero ->
                if (hero.id == databaseCard.id) {
                    hero.favorite = databaseCard.favorite
                    deck.add(hero)
                }
            }
        }
        return deck
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

                Firebase.auth.signOut()

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)

                finish()
            }
            .show()
    }
}