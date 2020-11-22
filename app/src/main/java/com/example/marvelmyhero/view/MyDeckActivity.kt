package com.example.marvelmyhero.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.marvelmyhero.R

class MyDeckActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_deck)

        val arrowBack = findViewById<ImageView>(R.id.img_arrowBack_myDeck)

        arrowBack.setOnClickListener {
            finish()
        }
    }
}