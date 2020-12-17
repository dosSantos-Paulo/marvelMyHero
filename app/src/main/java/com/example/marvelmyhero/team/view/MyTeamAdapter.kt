package com.example.marvelmyhero.team.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelmyhero.R
import com.example.marvelmyhero.card.model.Hero

class MyTeamAdapter(
    private val _cardListMyTeam: MutableList<Hero>,
    private val _listener: (Hero) -> Unit
) : RecyclerView.Adapter<MyTeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTeamViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.fragment_mini_card,
            parent, false
        )
        return MyTeamViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyTeamViewHolder, position: Int) {
        val item = _cardListMyTeam[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { _listener(item) }
    }

    override fun getItemCount() = _cardListMyTeam.size
}
