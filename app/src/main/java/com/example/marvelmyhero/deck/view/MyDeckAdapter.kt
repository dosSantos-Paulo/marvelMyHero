package com.example.marvelmyhero.deck.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelmyhero.R
import com.example.marvelmyhero.card.model.Hero

class MyDeckAdapter(
    private val _cardList: MutableList<Hero>,
    private val _listener: (Hero) -> Unit,
) : RecyclerView.Adapter<MyDeckViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyDeckViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.fragment_mini_card,
            parent,
            false
        )

        return MyDeckViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyDeckViewHolder, position: Int) {
        val item = _cardList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { _listener(item) }
    }

    override fun getItemCount() = _cardList.size
}