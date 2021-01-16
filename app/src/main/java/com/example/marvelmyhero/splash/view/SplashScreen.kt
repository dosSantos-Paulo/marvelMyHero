package com.example.marvelmyhero.splash.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.marvelmyhero.R
import com.example.marvelmyhero.utils.UserUtils
import com.example.marvelmyhero.data.repository.CharacterRepository
import com.example.marvelmyhero.db.database.AppDataBase
import com.example.marvelmyhero.db.entity.CardEntity
import com.example.marvelmyhero.db.repository.CardRepository
import com.example.marvelmyhero.db.viewmodel.CardViewModel
import com.example.marvelmyhero.login.view.LoginActivity
import com.example.marvelmyhero.login.view.LoginFragment.Companion.EMAIL_PREFS
import com.example.marvelmyhero.login.view.LoginFragment.Companion.KEEP_CONNECTED_PREFS
import com.example.marvelmyhero.login.view.LoginFragment.Companion.PASS_PREFS
import com.example.marvelmyhero.splash.viewmodel.CharacterViewModel
import com.example.marvelmyhero.main.view.MainActivity
import com.example.marvelmyhero.utils.CardManager
import pl.droidsonroids.gif.GifImageView

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val cardMaster = CardManager()

        getViewModel(cardMaster)
        animation()
        Handler(Looper.getMainLooper()).postDelayed({
            preferencesLogin()
        }, HANDLER_TIME)
    }

    private fun getViewModel(cardMaster: CardManager) {

        val viewModel = ViewModelProvider(
            this,
            CharacterViewModel.CharacterViewModelFactory(CharacterRepository())
        ).get(CharacterViewModel::class.java)

        val allCharId = cardMaster.getIdsList()

        viewModel.getCharacter(allCharId).observe(this) {
            cardMaster.addCardsOnManager(it)
            dbViewModel(cardMaster.getCardList())
        }
    }

    private fun dbViewModel(cardList: List<CardEntity>) {
        val databaseViewModel = ViewModelProvider(
            this,
            CardViewModel.CardViewModelFactory(
                CardRepository(
                    AppDataBase.getDatabase(this).cardDao()
                )
            )
        ).get(CardViewModel::class.java)

        cardList.forEach {
            databaseViewModel.addCard(it).observe(this) {
                Log.i("DB_INSERT", "$it")
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
        }, 5000)
    }

    private fun preferencesLogin() {
        val keepConnectedPreferences = getSharedPreferences(KEEP_CONNECTED_PREFS, MODE_PRIVATE)
        val email = keepConnectedPreferences.getString(EMAIL_PREFS, "")
        val password = keepConnectedPreferences.getString(PASS_PREFS, "")
        val userLogin = UserUtils.USER_MANAGER.login(email, password)

        if (userLogin == null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    companion object {
        const val HANDLER_TIME: Long = 10000
    }
}
