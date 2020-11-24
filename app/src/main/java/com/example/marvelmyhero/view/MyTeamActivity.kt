package com.example.marvelmyhero.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.HorizontalScrollView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelmyhero.R
import com.example.marvelmyhero.card.view.MiniCardFragment
import com.example.marvelmyhero.model.Hero
import com.example.marvelmyhero.view.MyDeckActivity.Companion.testCardColection

class MyTeamActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_team)

        val recyclerViewMyTeam = findViewById<RecyclerView>(R.id.recyclerView_myTeam)
        val viewManagerMyTeam = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val adapterMyTeam = MyTeamAdapter(testCardColection(10)) {
            Toast.makeText(this, getString(R.string.text_inserir_cards), Toast.LENGTH_LONG).show()
        }

        recyclerViewMyTeam.apply {
            setHasFixedSize(true)
            layoutManager = viewManagerMyTeam
            adapter = adapterMyTeam

        }

        miniCardFragment(
            "Dr. Strange",
            R.drawable.dr_strange,
            4.0,
            R.id.frameLayout_teamCard1_myTeam
        )

        miniCardFragment(
            "Captain America",
            R.drawable.captain_america,
            6.0,
            R.id.frameLayout_teamCard2_myTeam
        )

        miniCardFragment(
            "Black Widow",
            R.drawable.black_widow,
            2.0,
            R.id.frameLayout_teamCard3_myTeam
        )

    }

    private fun miniCardFragment(
        name: String,
        imageUrl: Int,
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