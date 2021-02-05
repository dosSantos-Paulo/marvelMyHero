package com.example.marvelmyhero.team.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelmyhero.R
import com.example.marvelmyhero.card.model.Hero
import com.example.marvelmyhero.utils.UserCardUtils.Companion.NEW_USER

class MyTeamActivity : AppCompatActivity() {
    private val deck = NEW_USER.getDeck()
    private val team = mutableListOf<Hero>()
    private val viewManagerMyTeam = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    private val adapterMyTeam = MyTeamAdapter(deck) {
        Toast.makeText(this, getString(R.string.text_inserir_cards), Toast.LENGTH_LONG).show()
        moveItem(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_team)

        val backArrowButton = findViewById<ImageView>(R.id.img_arrowBack_myTeam)
        val recyclerViewMyTeam = findViewById<RecyclerView>(R.id.recyclerView_myTeam)



        recyclerViewMyTeam.apply {
            setHasFixedSize(true)
            layoutManager = viewManagerMyTeam
            adapter = adapterMyTeam
        }



        backArrowButton.setOnClickListener {
            finish()
        }
    }

    fun moveItem(hero: Hero) {
        val indexTeam = team.indexOf(hero)
        val indexDeck = deck.indexOf(hero)

        if (indexDeck == -1) {
            deck.add(team.removeAt(indexTeam))
        } else {
            if (team.size < 3) {
                team.add(deck.removeAt(indexDeck))
            }
        }

        showTeamCards(team)
        adapterMyTeam.notifyDataSetChanged()
    }

    private fun showTeamCards(team: MutableList<Hero>) {

        for (i in team.indices) {
            var frameLayout = R.id.frameLayout_teamCard1_myTeam
            when (i) {
                1 -> frameLayout = R.id.frameLayout_teamCard2_myTeam

                2 -> frameLayout = R.id.frameLayout_teamCard3_myTeam
            }

            miniCardFragment(
                this,
                team[i],
                frameLayout
            )
        }
    }

    private fun miniCardFragment(
        activity: MyTeamActivity,
        hero: Hero,
        frame: Int
    ) {
        val newCard = MiniCardMyTeam(hero, activity)
        supportFragmentManager.beginTransaction().apply {
            replace(frame, newCard)
            commit()
        }
    }
}

