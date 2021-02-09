package com.example.marvelmyhero.team.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelmyhero.R
import com.example.marvelmyhero.card.model.Hero
import com.example.marvelmyhero.utils.UserVariables.MY_USER

class MyTeamActivity : AppCompatActivity() {

    private val deck = MY_USER!!.deck
    private val team = mutableListOf(deck.removeAt(0), deck.removeAt(0), deck.removeAt(0))

    private val viewManagerMyTeam = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    private val adapterMyTeam = MyTeamAdapter(deck) {
        moveItem(it)
    }
    private val updateDeck = mutableListOf<Hero>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_team)
        showTeamCards(team)

        val backArrowButton = findViewById<ImageView>(R.id.img_arrowBack_myTeam)
        val recyclerViewMyTeam = findViewById<RecyclerView>(R.id.recyclerView_myTeam)



        recyclerViewMyTeam.apply {
            setHasFixedSize(true)
            layoutManager = viewManagerMyTeam
            adapter = adapterMyTeam
        }



        backArrowButton.setOnClickListener {
            onBackPressed()
        }

    }


    override fun onBackPressed() {
        when (team.size) {
            3 -> {
                MY_USER!!.deck = updateDeck
                super.onBackPressed()
                finish()
            }
            0 -> {
                super.onBackPressed()
                finish()
            }
            else -> {
                Toast.makeText(this, "Your team needs to have 3 heroes", Toast.LENGTH_LONG).show()
            }
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

    private fun updateDeck() {
        updateDeck.clear()
        updateDeck.addAll(team)
        updateDeck.addAll(deck)
    }

    private fun showTeamCards(team: MutableList<Hero>) {

        updateDeck()

        if (team.size == 3) {
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
        } else if (team.size == 2) {
            for (i in team.indices) {
                var frameLayout = R.id.frameLayout_teamCard1_myTeam
                when (i) {
                    1 -> frameLayout = R.id.frameLayout_teamCard2_myTeam
                }

                miniCardFragment(
                    this,
                    team[i],
                    frameLayout
                )
            }

            miniCardFragment(
                this,
                chooseYourHero(),
                R.id.frameLayout_teamCard3_myTeam
            )
        } else if (team.size == 1) {

            miniCardFragment(
                this,
                team[0],
                R.id.frameLayout_teamCard1_myTeam
            )
            miniCardFragment(
                this,
                chooseYourHero(),
                R.id.frameLayout_teamCard2_myTeam
            )
            miniCardFragment(
                this,
                chooseYourHero(),
                R.id.frameLayout_teamCard3_myTeam
            )
        } else {
            miniCardFragment(
                this,
                chooseYourHero(),
                R.id.frameLayout_teamCard1_myTeam
            )
            miniCardFragment(
                this,
                chooseYourHero(),
                R.id.frameLayout_teamCard2_myTeam
            )
            miniCardFragment(
                this,
                chooseYourHero(),
                R.id.frameLayout_teamCard3_myTeam
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

    private fun chooseYourHero(): Hero {
        return Hero(
            0,
            "Your Hero",
            "",
            "https://fsb.zobj.net/crop.php?r=UtAvNNW2KH8H1Rq8zkxlMZxuS_9Vvm6fC6wRGLSLfpTUzkSFrHca7yhQz50fzuWaeM_iAy75YBGMgci2hwY6pnVYXWE9McCvVF-QqJwBOeEis6cOhIP7zadqRZXL2gRk3ksxIZXOEbRt229ymqKVgqzpMi7xA6SvwhBrUorYJEQJvyF-Cs0bRDTrHYw",
            0,
            0,
            0,
            0,
            0,
            0,
            ""
        )
    }
}