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
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.marvelmyhero.utils.MovieUtil
import com.example.marvelmyhero.R
import com.example.marvelmyhero.verifications.VerificationsActivity
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
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var button: Button
    private lateinit var callbackManager: CallbackManager
    private lateinit var myView: View
    lateinit var email: TextInputEditText
    lateinit var password: TextInputEditText

    private val viewModel: AuthenticationViewModel by lazy {
        ViewModelProvider(this).get(
            AuthenticationViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        Log.d("USER_FLUX", "-> Login")

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

        email = myView.findViewById(R.id.editText_email_login)
        password = myView.findViewById(R.id.editText_password_login)

        loginButton.setOnClickListener {
            if(validaCamposLogin()) {
                if (validaEmail()) {
                    viewModel.loginEmailPassword(email.text.toString(), password.text.toString())
                }
                else {
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
        Log.d("USER_FLUX", "-> login firebase")
//        Login com Firebase
        when {
            status -> {
                val intent = Intent(view?.context, VerificationsActivity::class.java)
                startActivity(intent)
            }
            else -> {
                Toast.makeText(myView.context, "Something Wrong", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun irParaHome(uiid: String?) {
        Log.d("USER_FLUX", "-> login social")
//      Login pelo Facebook e Google
        val intent = Intent(view?.context, VerificationsActivity::class.java)
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
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    irParaHome(null)
                } else {
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                }
            }
    }

    private fun validaCamposLogin(): Boolean {
        var resultado = true

        if (email.text.toString().isBlank()) {
            myView.findViewById<TextInputEditText>(R.id.editText_email_login).error =
                getString(R.string.campo_vazio)
            resultado = false
        }
        if (password.text.toString().isBlank()) {
            myView.findViewById<TextInputEditText>(R.id.editText_password_login).error =
                getString(R.string.campo_vazio)
            resultado = false
        }
        return resultado
    }

    private fun validaEmail(): Boolean {
        var resultado = true
        if (MovieUtil.validateEmailPasswordLogin(email.text.toString(), password.text.toString())) {
            myView.findViewById<TextInputEditText>(R.id.editText_email_login).error =
                getString(R.string.email_invalido)
            resultado = false
        }
        return resultado
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}


