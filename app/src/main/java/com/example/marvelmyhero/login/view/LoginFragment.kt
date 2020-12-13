package com.example.marvelmyhero.login.view

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.marvelmyhero.R
import com.example.marvelmyhero.view.MainActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {
    private  lateinit var button: Button
    private lateinit var callbackManager: CallbackManager
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
                val intent = Intent(view.context, MainActivity::class.java)
                startActivity(intent)
            }

            view.findViewById<MaterialButton>(R.id.btn_login_login).setOnClickListener {
                val intent = Intent(view.context, MainActivity::class.java)
                startActivity(intent)

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