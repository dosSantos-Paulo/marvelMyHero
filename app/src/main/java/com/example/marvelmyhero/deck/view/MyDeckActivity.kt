package com.example.marvelmyhero.deck.view


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelmyhero.R
import com.example.marvelmyhero.card.view.CardFrontFragment
import com.example.marvelmyhero.utils.Constants.DECK_COLLUMN
import com.example.marvelmyhero.utils.UserVariables.MY_USER

class MyDeckActivity : AppCompatActivity() {

    private val arrowBack: ImageView by lazy { findViewById<ImageView>(R.id.img_arrowBack_myDeck) }
    private val info: ImageView by lazy { findViewById<ImageView>(R.id.ic_info) }
    private val cardRecyclerView: RecyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView_myDeck) }
    private val cardManager = GridLayoutManager(this, DECK_COLLUMN)
    private var validadorRecycler = true

    private lateinit var cardAdapter: MyDeckAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_deck)

        cardAdapter = MyDeckAdapter(MY_USER!!.deck) {
            supportFragmentManager.beginTransaction().apply {
                setCustomAnimations(
                    R.anim.show_card,
                    R.anim.hide_card,
                    R.anim.show_card,
                    R.anim.hide_card,
                )

                replace(R.id.frameLayout_myDeck, CardFrontFragment(it))
                addToBackStack(null)
                commit()
            }

        }

        arrowBack.setOnClickListener {
            finish()
        }

        info.setOnClickListener {
            val intent = Intent(this, InfoMyDeckActivity::class.java)
            startActivity(intent)
        }

        recyclerView()
    }

    override fun onResume() {
        super.onResume()
        validadorRecycler = true
    }

    private fun recyclerView() {
        cardRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = cardManager
            adapter = cardAdapter
        }
    }
}