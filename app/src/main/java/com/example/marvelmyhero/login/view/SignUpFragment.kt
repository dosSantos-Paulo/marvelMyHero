package com.example.marvelmyhero.login.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marvelmyhero.R
import com.example.marvelmyhero.main.view.MainActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val signupButton = view.findViewById<MaterialButton>(R.id.btn_signup_signup)

        val name = view.findViewById<TextInputLayout>(R.id.editText_name_signUp).editText
        val email = view.findViewById<TextInputLayout>(R.id.editText_email_signUp).editText
        val password = view.findViewById<TextInputLayout>(R.id.editText_password_signUp).editText
        val repeatPassword =
            view.findViewById<TextInputLayout>(R.id.editText_repeatPassword_signUp).editText

        signupButton.setOnClickListener {
            if (name?.text?.trim().isNullOrEmpty()) {
                name?.error = getString(R.string.nameSignupError)
            }
            if (email?.text?.trim().isNullOrEmpty()) {
                email?.error = getString(R.string.email_error)
            }
            if (password?.text?.trim().isNullOrEmpty()) {
                password?.error = getString(R.string.password)
            } else if (password?.text?.trim()?.length!! !in 4..14) {
                password.error = getString(R.string.passwordLengthError)
            }
            if (repeatPassword?.text?.trim().isNullOrEmpty()) {
                repeatPassword?.error = getString(R.string.password)
            } else if (repeatPassword?.text?.trim() != password?.text?.trim()) {
                repeatPassword?.error = getString(R.string.repeatPasswordCompareError)
            }

            if (!name?.text?.trim().isNullOrEmpty() &&
                !email?.text?.trim().isNullOrEmpty() &&
                !password?.text?.trim().isNullOrEmpty() &&
                !repeatPassword?.text?.trim().isNullOrEmpty() &&
                password?.text?.trim()?.length!! in 4..14 &&
                repeatPassword?.text?.trim() == password?.text?.trim()
            ) {

                val intent = Intent(view.context, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}