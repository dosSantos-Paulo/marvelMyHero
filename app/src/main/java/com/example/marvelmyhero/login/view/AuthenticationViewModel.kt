package com.example.marvelmyhero.login.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.marvelmyhero.utils.MovieUtil
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class AuthenticationViewModel(application: Application) : AndroidViewModel(application) {
    var loading: MutableLiveData<Boolean> = MutableLiveData()
    var error: MutableLiveData<String> = MutableLiveData()
    var stateRegister:MutableLiveData<Boolean> = MutableLiveData()
    var stateLogin:MutableLiveData<Boolean> = MutableLiveData()

    fun registerUser(email:String, password:String){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                loading.value = false
                when{
                    task.isSuccessful ->{
                        MovieUtil.saveUserId(
                            getApplication(),
                            FirebaseAuth.getInstance().currentUser?.uid
                        )
                        stateRegister.value = true
                    }
                    else -> {
                        errorMessage("Authentication Failed!")
                    }
                }
            }
    }

    fun loginEmailPassword(email:String, password: String){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task: Task<AuthResult> ->

                when {
                    task.isSuccessful -> {
                        MovieUtil.saveUserId(
                            getApplication(),
                            FirebaseAuth.getInstance().currentUser?.uid
                        )
                        stateLogin.value = true
                    }
                    else -> {
                        errorMessage("Authentication Failed!")
                        stateLogin.value = false
                        loading.value = false
                    }
                }
            }
    }

    private fun errorMessage(message: String){
        error.value = message
    }
}