package com.example.marvelmyhero.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import com.example.marvelmyhero.R
import kotlinx.coroutines.delay

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val timeSplashScreen: Long = 3500

        animation()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, timeSplashScreen)
    }

    private fun animation() {
        val img = findViewById<ImageView>(R.id.img_splash_screen)
        img.animate().apply {
            duration = 2500
            scaleX(1.10f)
            scaleY(1.10f)
        }
    }
}
