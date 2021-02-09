package com.example.marvelmyhero.team.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelmyhero.R
import com.example.marvelmyhero.card.model.Hero
import com.example.marvelmyhero.utils.UserVariables.MY_USER

class MyTeamActivity : AppCompatActivity() {
    private val viewManagerMyTeam = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    private val recyclerViewMyTeam = findViewById<RecyclerView>(R.id.recyclerView_myTeam)
    private val backArrowButton by lazy { findViewById<ImageView>(R.id.img_arrowBack_myTeam) }
    private var _deck = MY_USER!!.deck
    private lateinit var _team: MutableList<Hero>
    private val adapterMyTeam = MyTeamAdapter(_deck) {
        moveItem(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_team)

        getTeam()

        recyclerViewMyTeam.apply {
            setHasFixedSize(true)
            layoutManager = viewManagerMyTeam
            adapter = adapterMyTeam
        }


        backArrowButton.setOnClickListener {
            onBackPressed()
        }

    }

    fun getTeam() {
        _team.add(_deck.removeAt(0))
        _team.add(_deck.removeAt(0))
        _team.add(_deck.removeAt(0))
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    fun moveItem(hero: Hero) {
        val indexTeam = _team.indexOf(hero)
        val indexDeck = _deck.indexOf(hero)

        if (indexDeck == -1) {
            _deck.add(_team.removeAt(indexTeam))
        } else {
            if (_team.size < 3) {
                _team.add(_deck.removeAt(indexDeck))
            }
        }

        showTeamCards(_team)
        adapterMyTeam.notifyDataSetChanged()
        updateDeck()
    }

    private fun updateDeck() {
        MY_USER!!.deck.clear()
        MY_USER!!.deck.addAll(_team)
        MY_USER!!.deck.addAll(_deck)
    }

    private fun showTeamCards(team: MutableList<Hero>) {

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

