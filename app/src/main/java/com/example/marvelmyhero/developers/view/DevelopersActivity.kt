package com.example.marvelmyhero.developers.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.marvelmyhero.R

class DevelopersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_developers)

        val backArrowButton = findViewById<ImageView>(R.id.img_arrowBack_Developers)

        backArrowButton.setOnClickListener {
            finish()
        }
    }
}