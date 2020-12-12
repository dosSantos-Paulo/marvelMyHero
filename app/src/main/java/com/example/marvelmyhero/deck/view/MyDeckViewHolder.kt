package com.example.marvelmyhero.deck.view

import android.graphics.Color
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelmyhero.R
import com.example.marvelmyhero.card.view.MiniCardFragment.Companion.getColor
import com.example.marvelmyhero.card.model.Hero
import com.squareup.picasso.Picasso

class MyDeckViewHolder(
    private val _view: View
) : RecyclerView.ViewHolder(_view) {

    private val _name = _view.findViewById<TextView>(R.id.txt_heroName_cardMini)
    private val _imageUrl = _view.findViewById<ImageView>(R.id.img_heroPic_cardMini)
    private val _cardColor = _view.findViewById<ConstraintLayout>(R.id.boderColor_cardMini)
    private val _favorite = _view.findViewById<ImageView>(R.id.ic_fav_cardMini)

    fun bind(card: Hero) {

        if (card.favorite) {
            _favorite.visibility = VISIBLE
        } else if (!card.favorite) {
            _favorite.visibility = INVISIBLE
        }

        itemView.setOnLongClickListener { v: View ->
            when (card.favorite) {
                true -> {
                    card.favorite = false
                    _favorite.visibility = INVISIBLE
                }
                false -> {
                    card.favorite = true
                    _favorite.visibility = VISIBLE
                }
            }
            true
        }

        _name.text = card.heroName
        _cardColor.setBackgroundColor(Color.parseColor(_view.context.getString(getColor(card.classification))))

        Picasso
            .get()
            .load(card.imageUrl)
            .into(_imageUrl)
    }
}
