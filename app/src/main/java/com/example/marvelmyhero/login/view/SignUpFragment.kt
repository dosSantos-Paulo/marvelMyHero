package com.example.marvelmyhero.login.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.marvelmyhero.R
import com.example.marvelmyhero.utils.Constants.userNameFromSignup
import com.example.marvelmyhero.utils.MovieUtil
import com.example.marvelmyhero.verifications.VerificationsActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class SignUpFragment : Fragment() {

    lateinit var name: TextInputEditText
    lateinit var email: TextInputEditText
    lateinit var password: TextInputEditText
    lateinit var repeatPassword: TextInputEditText
    private lateinit var signupButton: MaterialButton
    private lateinit var _view: View

    private val viewModel: AuthenticationViewModel by lazy {
        ViewModelProvider(this).get(AuthenticationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)
        _view = view
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        name = _view.findViewById(R.id.editText_name_signUp)
        email = _view.findViewById(R.id.editText_email_signUp)
        password = _view.findViewById(R.id.editText_password_signUp)
        repeatPassword = _view.findViewById(R.id.editText_repeatPassword_signUp)
        signupButton = _view.findViewById(R.id.btn_signup_signup)

        signupButton.setOnClickListener {
            if (validaCamposLogin()) {
                if (validaEmail()) {
                    viewModel.registerUser(email.text.toString(), password.text.toString())
                }
            }
            initViewModel(name.text.toString())
        }
    }

    private fun initViewModel(name: String) {
        viewModel.stateRegister.observe(viewLifecycleOwner) { state ->
            state?.let {
                navigateToHome(it, name)
            }
        }
    }

    private fun navigateToHome(status: Boolean, name: String) {
        when {
            status -> {
                val intent = Intent(context, VerificationsActivity::class.java)
                userNameFromSignup = _view.findViewById<TextInputEditText>(R.id.editText_name_signUp).text.toString()
                startActivity(intent)
            }
            else -> {
                Toast.makeText(_view.context, "Something Wrong", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showErrorMessage(message: String) {
        Snackbar.make(signupButton, message, Snackbar.LENGTH_LONG).show()
    }

    private fun validaCamposLogin(): Boolean {
        var resultado = true

        if (name.text.toString().isBlank()) {
            _view.findViewById<TextInputEditText>(R.id.editText_name_signUp).error =
                getString(R.string.campo_vazio)
            resultado = false
        }
        if (email.text.toString().isBlank()) {
            _view.findViewById<TextInputEditText>(R.id.editText_email_signUp).error =
                getString(R.string.campo_vazio)
            resultado = false
        }
        if (password.text.toString().isBlank()) {
            _view.findViewById<TextInputEditText>(R.id.editText_password_signUp).error =
                getString(R.string.campo_vazio)
            resultado = false
        }
        if (repeatPassword.text.toString().length < 6) {
            _view.findViewById<TextInputEditText>(R.id.editText_repeatPassword_signUp).error =
                getString(R.string.numero_caracteres)
            resultado = false
        }
        if (repeatPassword.text.toString().isBlank()) {
            _view.findViewById<TextInputEditText>(R.id.editText_repeatPassword_signUp).error =
                getString(R.string.campo_vazio)
            resultado = false
        }
        if (repeatPassword.text.toString() != password.text.toString()) {
            _view.findViewById<TextInputEditText>(R.id.editText_repeatPassword_signUp).error =
                getString(R.string.senha_diferente)
            _view.findViewById<TextInputEditText>(R.id.editText_password_signUp).error =
                getString(R.string.senha_diferente)
            resultado = false
        }
        return resultado
    }

    private fun validaEmail(): Boolean {
        var resultado = true
        if (
            MovieUtil.validateEmailPassword(email.text.toString(), password.text.toString())) {
            _view.findViewById<TextInputEditText>(R.id.editText_email_signUp).error =
                getString(R.string.email_invalido)
            resultado = false
        }
        return resultado
    }
}