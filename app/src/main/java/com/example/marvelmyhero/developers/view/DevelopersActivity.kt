package com.example.marvelmyhero.developers.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.marvelmyhero.R

class DevelopersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_developers)

        val backArrowButton = findViewById<ImageView>(R.id.img_arrowBack_Developers)
        val linkedinGabriel = findViewById<Button>(R.id.btn_Linkedin_Gabriel)
        val linkedinPaulo = findViewById<Button>(R.id.btn_Linkedin_Paulo)
        val linkedinRafael = findViewById<Button>(R.id.btn_Linkedin_Rafael)
        val gitHubGabriel = findViewById<Button>(R.id.btn_Github_Gabriel)
        val gitHubPaulo = findViewById<Button>(R.id.btn_Github_Paulo)
        val gitHubRafael = findViewById<Button>(R.id.btn_Github_Rafael)

        gitHubGabriel.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW,
                Uri.parse("https://github.com/Marrani"))
            startActivity(intent)
        }

        gitHubPaulo.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW,
                Uri.parse("https://github.com/dosSantos-Paulo"))
            startActivity(intent)
        }

        gitHubRafael.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW,
                Uri.parse("https://github.com/RafaelIrineu"))
            startActivity(intent)
        }

        linkedinGabriel.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.linkedin.com/in/gabriel-henrique-marrani-nunes-segantini-2158b9182/"))
            startActivity(intent)
        }

        linkedinPaulo.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.linkedin.com/in/dossantos-paulo/"))
            startActivity(intent)
        }

        linkedinRafael.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.linkedin.com/in/rafael-costa-6001b015b/"))
            startActivity(intent)
        }

        backArrowButton.setOnClickListener {
            finish()
        }
    }
}