package com.example.marvelmyhero.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.marvelmyhero.R
import com.example.marvelmyhero.card.view.MiniCardFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userButton = findViewById<ImageView>(R.id.img_userIcon_main)
        val exitButton = findViewById<ImageView>(R.id.ic_exit_main)

        miniCardFragment(
            "Dr. Strange",
            R.drawable.dr_strange,
            4.0,
            R.id.frameLayout_teamCard1_main)

        miniCardFragment(
            "Captain America",
            R.drawable.captain_america,
            6.0,
            R.id.frameLayout_teamCard2_main)

        miniCardFragment(
            "Black Widow",
            R.drawable.black_widow,
            2.0,
            R.id.frameLayout_teamCard3_main)

        userButton.setOnClickListener {
            newUserFragment()
        }

        exitButton.setOnClickListener {
            exitDialog()
        }
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

    private fun newUserFragment() {

        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(
                R.anim.from_left_user_card,
                R.anim.to_left_user_card,
                R.anim.from_left_user_card,
                R.anim.to_left_user_card
            )
            replace(R.id.frameLayout_userDetail_main, UserFragment())
            addToBackStack(null)
            commit()
        }
    }

    private fun exitDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.exitDialog_title))
            .setMessage(getString(R.string.exitDialog_message))
            .setNegativeButton(getString(R.string.exitDialog_negativeButton)) { _, _ ->
                closeContextMenu()
            }
            .setPositiveButton(getString(R.string.exitDialog_positiveButton)) { _, _ ->
                finish()
            }
            .show()
    }
}