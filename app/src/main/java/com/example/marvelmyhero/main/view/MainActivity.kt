package com.example.marvelmyhero.main.view



import android.content.*
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.marvelmyhero.R
import com.example.marvelmyhero.card.model.Hero
import com.example.marvelmyhero.card.view.MiniCardFragment
import com.example.marvelmyhero.db.database.AppDataBase
import com.example.marvelmyhero.db.repository.CardRepository
import com.example.marvelmyhero.db.viewmodel.CardViewModel
import com.example.marvelmyhero.deck.view.MyDeckActivity
import com.example.marvelmyhero.developers.view.DevelopersActivity
import com.example.marvelmyhero.login.model.User
import com.example.marvelmyhero.login.view.LoginActivity
import com.example.marvelmyhero.team.view.MyTeamActivity
import com.example.marvelmyhero.utils.AlertManager
import com.example.marvelmyhero.utils.CardManager
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
    private var cardAlert = AlertManager(this)
    private val cardManager = CardManager()


    //    Firebase
    private val firebaseUser = FirebaseAuth.getInstance().currentUser
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val storageRef =
        FirebaseStorage.getInstance().getReference(firebaseUser?.uid.toString())
    private var myRef = firebaseDatabase.getReference(firebaseUser?.uid.toString())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initDbViewModel()

        storageRef.downloadUrl.addOnSuccessListener {
            imageUri = it
            toolBarItems(MY_USER!!)
        }

        if (IS_MY_FIRST_TIME_ON_APP) {
            cardAlert.newCardAlert(cardManager, MY_USER!!.deck, false)
        }

        val team = mutableListOf(MY_USER!!.deck[0], MY_USER!!.deck[1], MY_USER!!.deck[2])

        showTeamCards(team)

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

        Picasso.get().load(imageUri).into(userImage)
        userName.text = user.nickName

        userImage.setOnClickListener {
            newUserFragment(user)
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

                Firebase.auth.signOut()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                MY_USER = null
                finish()

            }
            .show()
    }
}