package com.example.marvelmyhero.login.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.marvelmyhero.utils.MovieUtil.validateNameEmailPassword
import com.example.marvelmyhero.R
import com.example.marvelmyhero.login.viewmodel.AuthenticationViewModel
import com.example.marvelmyhero.register.RegisterActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar

class SignUpFragment : Fragment() {
    private  lateinit var signupButton: Button
    private val viewModel: AuthenticationViewModel by lazy {
        ViewModelProvider(this).get(
            AuthenticationViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signupButton = view.findViewById<MaterialButton>(R.id.btn_signup_signup)


        signupButton.setOnClickListener {
            val name = view.findViewById<EditText>(R.id.editText_name_signUp).text.toString()
            val email = view.findViewById<EditText>(R.id.editText_email_signUp).text.toString()
            val password = view.findViewById<EditText>(R.id.editText_password_signUp).text.toString()
            when {
                validateNameEmailPassword(name, email, password) -> {
                    viewModel.registerUser(email, password)
                }
            }
            initViewModel()
        }
    }

    private fun initViewModel(){
        viewModel.stateRegister.observe(viewLifecycleOwner, {state ->
            state?.let{
                navigateToHome(it)
            }
        })
    }

    private fun navigateToHome(status: Boolean) {
        when {
            status -> {
                val intent = Intent(context, RegisterActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun showErrorMessage(message: String) {
        Snackbar.make(signupButton, message, Snackbar.LENGTH_LONG).show()
    }


}