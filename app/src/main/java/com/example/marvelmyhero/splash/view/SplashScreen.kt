package com.example.marvelmyhero.splash.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.marvelmyhero.R
import com.example.marvelmyhero.data.repository.CharacterRepository
import com.example.marvelmyhero.db.database.AppDataBase
import com.example.marvelmyhero.db.entity.CardEntity
import com.example.marvelmyhero.db.repository.CardRepository
import com.example.marvelmyhero.db.viewmodel.CardViewModel
import com.example.marvelmyhero.main.view.MainActivity
import com.example.marvelmyhero.splash.viewmodel.CharacterViewModel
import com.example.marvelmyhero.utils.CardManager
import com.example.marvelmyhero.utils.Constants.HANDLER_TIME
import com.example.marvelmyhero.utils.Constants.HANDLER_TIME_ANIMATION
import com.example.marvelmyhero.utils.Constants.HANDLER_TIME_ANIMATION_2
import pl.droidsonroids.gif.GifImageView

@Suppress("COMPATIBILITY_WARNING")
class SplashScreen : AppCompatActivity() {

    private lateinit var databaseViewModel: CardViewModel

    private lateinit var viewModel: CharacterViewModel

    private val cardManager = CardManager()

    val progressBar: ProgressBar by lazy { findViewById(R.id.progressBar_splash) }


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

                Log.d("DB_COUNT:", it.toString())

                Handler(Looper.getMainLooper()).postDelayed({

                    callNextPage()

                }, HANDLER_TIME)

            } else {
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

            callNextPage()

        }
    }

    private fun createDatabase(cardList: List<CardEntity>) {

        cardList.forEach {
            databaseViewModel.addCard(it).observe(this) { isAdd ->
                Log.i("DB_INSERT", "$isAdd")
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

            }, HANDLER_TIME_ANIMATION_2)

        }, HANDLER_TIME_ANIMATION)

    }

    private fun callNextPage() {

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()

    }

}
