package com.example.marvelmyhero.view

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelmyhero.R
import com.example.marvelmyhero.card.view.MiniCardFragment.Companion.getColor
import com.example.marvelmyhero.model.Hero
import com.squareup.picasso.Picasso

class MyDeckViewHolder(
    private val _view: View
): RecyclerView.ViewHolder(_view){

    private val _name = _view.findViewById<TextView>(R.id.txt_heroName_cardMini)
    private val _imageUrl = _view.findViewById<ImageView>(R.id.img_heroPic_cardMini)
    private val _cardColor = _view.findViewById<ConstraintLayout>(R.id.boderColor_cardMini)

    fun bind(card: Hero) {
        _name.text = card.heroName
        _cardColor.setBackgroundColor(Color.parseColor(_view.context.getString(getColor(card.classification))))

        Picasso
            .get()
            .load(card.imageUrl)
            .into(_imageUrl)
    }
}
