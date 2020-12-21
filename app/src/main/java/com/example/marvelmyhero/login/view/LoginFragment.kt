package com.example.marvelmyhero.login.view

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.marvelmyhero.MovieUtil
import com.example.marvelmyhero.R
import com.example.marvelmyhero.login.viewmodel.AuthenticationViewModel
import com.example.marvelmyhero.view.MainActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {
    private  lateinit var button: Button
    private lateinit var callbackManager: CallbackManager
    private val viewModel: AuthenticationViewModel by lazy {
        ViewModelProvider(this).get(
            AuthenticationViewModel::class.java
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_login, container, false)
        button = view.findViewById<Button>(R.id.btn_facebookLogin_login)
       // button.setFragment(this)
        callbackManager = CallbackManager.Factory.create()
        button.setOnClickListener { loginFacebook() }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val loginButton = view.findViewById<MaterialButton>(R.id.btn_login_login)
        val googleLogin = view.findViewById<MaterialButton>(R.id.btn_googleLogin_login)
        val facebookLogin = view.findViewById<MaterialButton>(R.id.btn_facebookLogin_login)



        loginButton.setOnClickListener {
            val email = view.findViewById<EditText>(R.id.editText_email_login).text.toString()
            val password = view.findViewById<EditText>(R.id.editText_password_login).text.toString()
            when {
                MovieUtil.validateEmailPassword(email, password) -> {
                    viewModel.loginEmailPassword(email, password)
                }
                else -> {
                    Snackbar.make(loginButton, "Login failed", Snackbar.LENGTH_LONG).show()
                }
            }

            initViewModel()
        }




    }

    private fun initViewModel(){
        viewModel.stateLogin.observe(viewLifecycleOwner, { state ->
            state?.let {
                navigateToHome(it)
            }
        })
    }

    private fun navigateToHome(status: Boolean) {
        when {
            status -> {
                startActivity(Intent(context, MainActivity::class.java))
            }
        }
    }

    private fun irParaHome(uiid: String) {
        startActivity(Intent(context, MainActivity::class.java))
    }


    private fun loginFacebook() {
        val instanceFirebase = LoginManager.getInstance()

        instanceFirebase.logInWithReadPermissions(this, listOf("email", "public_profile"))
        instanceFirebase.registerCallback(callbackManager, object :
            FacebookCallback<LoginResult> {

            override fun onSuccess(loginResult: LoginResult) {
                val credential: AuthCredential = FacebookAuthProvider.getCredential(loginResult.accessToken.token)
                FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener { irParaHome(loginResult.accessToken.userId) }
            }

            override fun onCancel() {
                Toast.makeText(context, "Cancelado!", Toast.LENGTH_SHORT).show()
            }

            override fun onError(error: FacebookException) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}