package com.example.marvelmyhero.card.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.marvelmyhero.R
import com.example.marvelmyhero.card.model.Hero


class CardBackFragment(private val _card: Hero) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_card_back, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cardName = view.findViewById<TextView>(R.id.txt_heroName_cardBack)
        val cardRealName = view.findViewById<TextView>(R.id.txt_heroRealName_cardBack)
        val cardHistory = view.findViewById<TextView>(R.id.txt_history_cardBack)

        val durability = view.findViewById<TextView>(R.id.txt_durability_cardBack)
        val energy = view.findViewById<TextView>(R.id.txt_energy_cardBack)
        val fightingSkills = view.findViewById<TextView>(R.id.txt_fightingSkills_cardBack)
        val inteligence = view.findViewById<TextView>(R.id.txt_inteligence_cardBack)
        val speed = view.findViewById<TextView>(R.id.txt_speed_cardBack)
        val strength = view.findViewById<TextView>(R.id.txt_strength_cardBack)

        val durabilitBar = view.findViewById<ImageView>(R.id.bar_durability_cardBack)
        val energyBar = view.findViewById<ImageView>(R.id.bar_energy_cardBack)
        val fightingSkillsBar = view.findViewById<ImageView>(R.id.bar_fightingSkills_cardBack)
        val inteligenceBar = view.findViewById<ImageView>(R.id.bar_inteligence_cardBack)
        val speedBar = view.findViewById<ImageView>(R.id.bar_speed_cardBack)
        val strengthBar = view.findViewById<ImageView>(R.id.bar_strength_cardBack)

        cardName.text = _card.heroName
        cardRealName.text = _card.name
        cardHistory.text = _card.description

        durability.text = _card.durability.toString()
        energy.text = _card.energy.toString()
        fightingSkills.text = _card.fightingSkills.toString()
        inteligence.text = _card.inteligence.toString()
        speed.text = _card.speed.toString()
        strength.text = _card.strength.toString()

        getBarSizeAnimation(durabilitBar, _card.durability.toFloat())
        getBarSizeAnimation(energyBar, _card.energy.toFloat())
        getBarSizeAnimation(fightingSkillsBar, _card.fightingSkills.toFloat())
        getBarSizeAnimation(inteligenceBar, _card.inteligence.toFloat())
        getBarSizeAnimation(speedBar, _card.speed.toFloat())
        getBarSizeAnimation(strengthBar, _card.strength.toFloat())
    }

    private fun getBarSizeAnimation(bar: ImageView, heroAttribute: Float) {
        val getValue = 50 * heroAttribute
        bar.animate().apply {
            duration = 3000
            translationX(1f)
            translationXBy(getValue)
        }
    }


}