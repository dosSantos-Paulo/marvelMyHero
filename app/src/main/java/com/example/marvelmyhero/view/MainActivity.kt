package com.example.marvelmyhero.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import com.example.marvelmyhero.R
import com.example.marvelmyhero.UserFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userButton = findViewById<ImageView>(R.id.img_userIcon_main)
        val exitButton = findViewById<ImageView>(R.id.ic_exit_main)

        userButton.setOnClickListener {
            newUserFragment()
        }

        exitButton.setOnClickListener {
            exitDialog()
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