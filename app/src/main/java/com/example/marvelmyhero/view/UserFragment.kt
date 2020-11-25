package com.example.marvelmyhero.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.marvelmyhero.R
import com.example.marvelmyhero.model.User
import com.squareup.picasso.Picasso


class UserFragment(
    private val _user: User
) : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userImage = view.findViewById<ImageView>(R.id.img_user_fragmentUser)
        val userName = view.findViewById<TextView>(R.id.txt_userName_fragmentUser)
        val totalCard = view.findViewById<TextView>(R.id.txt_totalCards_fragmentUser)

        Picasso.get().load(_user.imageUrl).into(userImage)
        userName.text = _user.nickName
        totalCard.text = _user.deck.size.toString()
        
    }
}