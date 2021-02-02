package com.example.marvelmyhero.card.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.marvelmyhero.R
import com.example.marvelmyhero.card.model.Hero
import com.example.marvelmyhero.utils.Constants.enterAnim
import com.example.marvelmyhero.utils.Constants.exitAnim
import com.example.marvelmyhero.utils.Constants.isDoubleBackPressed
import com.example.marvelmyhero.utils.Constants.popEnterAnim
import com.example.marvelmyhero.utils.Constants.popExitAnim
import com.google.android.material.card.MaterialCardView
import com.squareup.picasso.Picasso

class CardFrontFragment(private val _card: Hero) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_card_front, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cardName = view.findViewById<TextView>(R.id.txt_heroName_cardFront)
        val cardRealName = view.findViewById<TextView>(R.id.txt_heroRealName_cardFront)
        val cardClassification = view.findViewById<TextView>(R.id.txt_classification_cardFront)
        val cardImage = view.findViewById<ImageView>(R.id.img_heroPic_cardFront)
        val frameLayout = view.findViewById<FrameLayout>(R.id.frameLayout_cardFront)
        val cardView = view.findViewById<MaterialCardView>(R.id.cardView_cardFront)

        cardName.text = _card.heroName
        cardRealName.text = _card.name
        cardClassification.text = getClassification(_card.classification, view)
        Picasso.get().load(_card.imageUrl).into(cardImage)

        frameLayout.setOnClickListener {
            activity?.onBackPressed()
        }


        cardView.setOnClickListener {
            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
            fragmentManager.beginTransaction().apply {
                setCustomAnimations(
                    enterAnim,
                    exitAnim,
                    popEnterAnim,
                    popExitAnim,
                )
                replace(R.id.frameLayout_myDeck, CardBackFragment(_card))
                addToBackStack(null)
                commit()
            }
        }
    }

    companion object {
        fun getClassification(value: Double, view: View): String {
            var getStars = ""
            val starValue = view.context.getString(R.string.classificationStar)

            when (value) {
                in 0.1..2.9 -> {
                    getStars = starValue
                }
                in 3.0..4.5 -> {
                    getStars = "$starValue $starValue"
                }
                in 4.6..5.9 -> {
                    getStars = "$starValue $starValue $starValue"
                }
                in 6.0..7.0 -> {
                    getStars = "$starValue $starValue $starValue $starValue"
                }
            }
            return getStars
        }
    }
}