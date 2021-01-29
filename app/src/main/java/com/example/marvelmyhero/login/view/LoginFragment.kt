package com.example.marvelmyhero.login.view

import android.app.Activity.RESULT_OK
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.marvelmyhero.utils.MovieUtil
import com.example.marvelmyhero.R
import com.example.marvelmyhero.login.viewmodel.AuthenticationViewModel
import com.example.marvelmyhero.main.view.MainActivity
import com.example.marvelmyhero.register.RegisterActivity
import com.example.marvelmyhero.utils.Constants.NAME
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var button: Button
    private lateinit var callbackManager: CallbackManager
    private lateinit var myView: View

    private val viewModel: AuthenticationViewModel by lazy {
        ViewModelProvider(this).get(
            AuthenticationViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val view = inflater.inflate(R.layout.fragment_login, container, false)
        button = view.findViewById(R.id.btn_facebookLogin_login)
        // button.setFragment(this)
        callbackManager = CallbackManager.Factory.create()
        button.setOnClickListener { loginFacebook() }

        myView = view

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth

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
        googleLogin.setOnClickListener {
            val gso =
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build()
            val cliente = GoogleSignIn.getClient(requireContext(), gso)
            startActivityForResult(cliente.signInIntent, 1)
        }

    }

    private fun initViewModel() {
        viewModel.stateLogin.observe(viewLifecycleOwner) { state ->
            state?.let {
                navigateToHome(it)
            }
        }
    }

    private fun navigateToHome(status: Boolean) {
        when {
            status -> {

                val intent = Intent(view?.context, RegisterActivity::class.java)
                intent.putExtra(NAME, "")
                startActivity(intent)

            }
        }
    }

    private fun irParaHome(uiid: String) {

        val intent = Intent(view?.context, RegisterActivity::class.java)
        intent.putExtra(NAME, "")
        startActivity(intent)
    }

    private fun irParaHome2() {
//        Login pelo Google
        val intent = Intent(view?.context, RegisterActivity::class.java)
        intent.putExtra(NAME, "")
        startActivity(intent)

    }

    private fun loginFacebook() {
        val instanceFirebase = LoginManager.getInstance()

        instanceFirebase.logInWithReadPermissions(this, listOf("email", "public_profile"))
        instanceFirebase.registerCallback(callbackManager, object :
            FacebookCallback<LoginResult> {

            override fun onSuccess(loginResult: LoginResult) {
                val credential: AuthCredential =
                    FacebookAuthProvider.getCredential(loginResult.accessToken.token)
                FirebaseAuth.getInstance().signInWithCredential(credential)
                    .addOnCompleteListener { irParaHome(loginResult.accessToken.userId) }
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

        if (resultCode == RESULT_OK && requestCode == 1) {
            val contaGoogle = GoogleSignIn.getSignedInAccountFromIntent(data).result
            Log.i(TAG, "onActivityResult: conta google autenticada $contaGoogle")
        }

        if (requestCode == 1) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Log.w(TAG, "Google sign in failed", e)
                // ...
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    irParaHome2()
                } else {
                    Log.w(TAG, "signInWithCredential:failure", task.exception)

                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}


