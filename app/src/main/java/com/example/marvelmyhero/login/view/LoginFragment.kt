package com.example.marvelmyhero.login.view

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.marvelmyhero.R
import com.example.marvelmyhero.main.view.MainActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.example.marvelmyhero.utils.UserUtils.Companion.USER_MANAGER


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loginButton = view.findViewById<MaterialButton>(R.id.btn_login_login)
        val googleLogin = view.findViewById<MaterialButton>(R.id.btn_googleLogin_login)
        val facebookLogin = view.findViewById<MaterialButton>(R.id.btn_facebookLogin_login)

        val email = view.findViewById<TextInputLayout>(R.id.editText_email_login).editText
        val password = view.findViewById<TextInputLayout>(R.id.editText_password_login).editText

        loginButton.setOnClickListener {
            if (email?.text?.trim().isNullOrEmpty()) {
                email?.error = getString(R.string.email_error)
            }
            if (password?.text?.trim().isNullOrEmpty()) {
                password?.error = getString(R.string.password_error)
            }
            if (!email?.text?.trim().isNullOrEmpty() && !password?.text?.trim().isNullOrEmpty()) {

                val newEmail = email!!.text.trim().toString()
                val newPassword = password!!.text.trim().toString()
                val userLogin = USER_MANAGER.login(newEmail, newPassword)

                if (userLogin != null) {

                    val keepConnectedPreference = view.context.getSharedPreferences(
                        KEEP_CONNECTED_PREFS, MODE_PRIVATE
                    )

                    keepConnectedPreference.edit()
                        .putString(EMAIL_PREFS, newEmail)
                        .putString(PASS_PREFS, newPassword)
                        .apply()

                    val intent = Intent(view.context, MainActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(
                        view.context,
                        getString(R.string.invalidLoginError),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }


    }


    companion object {
        const val KEEP_CONNECTED_PREFS = "SAVE_LOGIN_PREFERENCES"
        const val EMAIL_PREFS = "EMAIL"
        const val PASS_PREFS = "PASSWORD"
    }
}