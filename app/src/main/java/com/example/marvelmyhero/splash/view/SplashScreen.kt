package com.example.marvelmyhero.splash.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.example.marvelmyhero.R
import com.example.marvelmyhero.utils.UserUtils
import com.example.marvelmyhero.data.repository.CharacterRepository
import com.example.marvelmyhero.login.view.LoginActivity
import com.example.marvelmyhero.login.view.LoginFragment.Companion.EMAIL_PREFS
import com.example.marvelmyhero.login.view.LoginFragment.Companion.KEEP_CONNECTED_PREFS
import com.example.marvelmyhero.login.view.LoginFragment.Companion.PASS_PREFS
import com.example.marvelmyhero.utils.CardUtils
import com.example.marvelmyhero.utils.CardUtils.Companion.BLACK_PANTHER
import com.example.marvelmyhero.utils.CardUtils.Companion.BLACK_WIDOW
import com.example.marvelmyhero.utils.CardUtils.Companion.CAPTAIN
import com.example.marvelmyhero.utils.CardUtils.Companion.IRON_MAN
import com.example.marvelmyhero.utils.CardUtils.Companion.LOKI
import com.example.marvelmyhero.utils.CardUtils.Companion.NICK_FURY
import com.example.marvelmyhero.utils.CardUtils.Companion.SPIDER_MAN
import com.example.marvelmyhero.utils.CardUtils.Companion.STRANGE
import com.example.marvelmyhero.utils.CardUtils.Companion.THANOS
import com.example.marvelmyhero.utils.CardUtils.Companion.THOR
import com.example.marvelmyhero.splash.viewmodel.CharacterViewModel
import com.example.marvelmyhero.main.view.MainActivity

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        UserUtils().startListOfUsers()

        getViewModel()

        animation()

        Handler(Looper.getMainLooper()).postDelayed({

            preferencesLogin()

        }, HANDLER_TIME)

    }

    private fun getViewModel() {

        val viewModel = ViewModelProvider(
            this,
            CharacterViewModel.CharacterViewModelFactory(CharacterRepository())
        ).get(CharacterViewModel::class.java)

        val allCharId =
            listOf(
                THANOS,
                STRANGE,
                CAPTAIN,
                NICK_FURY,
                IRON_MAN,
                BLACK_PANTHER,
                BLACK_WIDOW,
                SPIDER_MAN,
                THOR,
                LOKI
            )

        viewModel.getCharacter(allCharId).observe(this) {
            CardUtils().addCardOnManager(it)
        }

    }


    private fun animation() {
        findViewById<ImageView>(R.id.img_splash_screen)
            .animate().apply {
                duration = 2500
                scaleX(1.10f)
                scaleY(1.10f)
            }
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
