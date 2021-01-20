package com.example.marvelmyhero.deck.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.marvelmyhero.R
import com.example.marvelmyhero.card.model.Hero
import com.example.marvelmyhero.card.view.MiniCardFragment
import com.example.marvelmyhero.utils.UserCardUtils

class InfoMyDeckActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_my_deck)

        val arrowBack = findViewById<ImageView>(R.id.img_arrowBack_Info)

        arrowBack.setOnClickListener {
            finish()
        }

//        showTeamCards(UserCardUtils.NEW_USER.getTeam())
//
//    }
//
//    private fun showTeamCards(team: MutableList<Hero>) {
//
//        for (i in team.indices) {
//            var frameLayout = R.id.frameLayout_info_card1
//            when (i) {
//                1 -> frameLayout = R.id.frameLayout_info_card2
//
//                2 -> frameLayout = R.id.frameLayout_info_card3
//
//                3 -> frameLayout = R.id.frameLayout_info_card4
//            }
//
//            miniCardFragment(
//                team[i].heroName,
//                team[i].imageUrl,
//                team[i].classification,
//                frameLayout
//            )
//        }
//    }
//
//    private fun miniCardFragment(
//        name: String,
//        imageUrl: String,
//        classification: Double,
//        frame: Int,
//    ) {
//        val newCard = MiniCardFragment(name, imageUrl, classification)
//        supportFragmentManager.beginTransaction().apply {
//            replace(frame, newCard)
//            commit()
//        }
    }
}