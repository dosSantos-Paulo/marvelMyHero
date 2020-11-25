package com.example.marvelmyhero.login.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.marvelmyhero.R
import com.example.marvelmyhero.login.viewmodel.IMudarTab
import com.example.marvelmyhero.view.MainActivity
import com.google.android.material.textfield.TextInputEditText


class LoginFragment : Fragment() {

    private lateinit var mudarTabListener: IMudarTab
    private lateinit var minhaView: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btn_login_loginFragment).setOnClickListener {
            val intent = Intent(view.context, MainActivity::class.java)
            startActivity(intent)

        }
    }


}