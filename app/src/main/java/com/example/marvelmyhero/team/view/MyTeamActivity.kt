package com.example.marvelmyhero.team.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelmyhero.R
import com.example.marvelmyhero.card.view.MiniCardFragment
import com.example.marvelmyhero.card.model.Hero
import com.example.marvelmyhero.utils.UserCardUtils.Companion.NEW_USER

class MyTeamActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_team)

        val backArrowButton = findViewById<ImageView>(R.id.img_arrowBack_myTeam)
        val deck = NEW_USER.getDeck()
        val recyclerViewMyTeam = findViewById<RecyclerView>(R.id.recyclerView_myTeam)
        val viewManagerMyTeam = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val adapterMyTeam = MyTeamAdapter(deck) {
            Toast.makeText(this, getString(R.string.text_inserir_cards), Toast.LENGTH_LONG).show()
        }

        recyclerViewMyTeam.apply {
            setHasFixedSize(true)
            layoutManager = viewManagerMyTeam
            adapter = adapterMyTeam
        }

        showTeamCards(NEW_USER.getTeam())

        backArrowButton.setOnClickListener {
            finish()
        }
    }

    private fun showTeamCards(team: MutableList<Hero>) {

        for (i in team.indices) {
            var frameLayout = R.id.frameLayout_teamCard1_myTeam
            when (i) {
                1 -> frameLayout = R.id.frameLayout_teamCard2_myTeam

                2 -> frameLayout = R.id.frameLayout_teamCard3_myTeam
            }

            miniCardFragment(
                team[i].heroName,
                team[i].imageUrl,
                team[i].classification,
                frameLayout
            )
        }
    }

    private fun miniCardFragment(
        name: String,
        imageUrl: String,
        classification: Double,
        frame: Int
    ) {
        val newCard = MiniCardFragment(name, imageUrl, classification)
        supportFragmentManager.beginTransaction().apply {
            replace(frame, newCard)
            commit()
        }
    }
}