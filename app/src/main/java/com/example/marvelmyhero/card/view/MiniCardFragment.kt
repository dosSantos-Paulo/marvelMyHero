package com.example.marvelmyhero.card.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.marvelmyhero.R
import com.squareup.picasso.Picasso

class MiniCardFragment(
    private val name: String,
    private val imageUrl: String,
    private val cardClassification: Double
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mini_card, container, false)
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cardColor = view.findViewById<ConstraintLayout>(R.id.boderColor_cardMini)
        val cardName = view.findViewById<TextView>(R.id.txt_heroName_cardMini)
        val cardImage = view.findViewById<ImageView>(R.id.img_heroPic_cardMini)


        cardColor.setBackgroundColor(Color.parseColor(getString(getColor(cardClassification))))
        cardName.text = name
        Picasso.get().load(imageUrl).into(cardImage)
    }

    companion object {
        fun getColor (classification: Double): Int {
            var color = R.color.commumCardColor

            when (classification) {
                in 0.1..2.9 -> color = R.string.bronzeCardColor
                in 3.0..4.5 -> color = R.string.silverCardColor
                in 4.6..5.9 -> color = R.string.goldCardColor
                in 6.0..7.0 -> color = R.string.diamondCardColor
            }

            return color
        }
    }

}