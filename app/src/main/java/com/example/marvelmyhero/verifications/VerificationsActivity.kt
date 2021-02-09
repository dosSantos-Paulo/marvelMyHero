package com.example.marvelmyhero.verifications

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.marvelmyhero.R
import com.example.marvelmyhero.login.model.User
import com.example.marvelmyhero.main.view.MainActivity
import com.example.marvelmyhero.register.RegisterActivity
import com.example.marvelmyhero.utils.Constants
import com.example.marvelmyhero.utils.Constants.NAME
import com.example.marvelmyhero.utils.Constants.userNameFromSignup
import com.example.marvelmyhero.utils.UserVariables.IS_MY_FIRST_TIME_ON_APP
import com.example.marvelmyhero.utils.UserVariables.MY_USER
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

private var imageUri: Uri? = null
private val firebaseUser = FirebaseAuth.getInstance().currentUser
private val firebaseDatabase = FirebaseDatabase.getInstance()
private val storageRef = FirebaseStorage.getInstance().getReference(firebaseUser?.uid.toString())
private var myRef = firebaseDatabase.getReference(firebaseUser?.uid.toString())

class VerificationsActivity : AppCompatActivity() {

    data class DatabaseCard(
        val favorite: Boolean = false,
        val id: Int = 0,
    )

    data class DatabaseUser(
        val nickName: String = "",
        val imageUrl: String = "",
        val deck: MutableList<DatabaseCard>? = null
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verifications)

        Log.d("USER_FLUX", "-> verification")

        isNewUser()
    }


    private fun isNewUser() {
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(DatabaseUser::class.java)
                IS_MY_FIRST_TIME_ON_APP = value == null

                Log.d("USER_FLUX", "-> is first time on app ? $IS_MY_FIRST_TIME_ON_APP")

                if (!IS_MY_FIRST_TIME_ON_APP) {
                    storageRef.downloadUrl.addOnSuccessListener {
                        imageUri = it
                        Log.d("USER_FLUX", "-> imageUri $imageUri")
                        Log.d("USER_FLUX", "-> control deck ${MY_USER!!.deck}")
                        Handler(Looper.getMainLooper()).postDelayed({
                            Log.d("USER_FLUX", "-> mainactivity")
                            val intent =
                                Intent(this@VerificationsActivity, MainActivity::class.java)
                            intent.putExtra(Constants.IMAGE, imageUri.toString())
                            startActivity(intent)
                            finish()
                        }, Constants.HANDLER_TIME_BRIDGE_2)
                    }
                } else {
                    Log.d("USER_FLUX", "-> novo usuario -> $MY_USER")
                    if (MY_USER == null){
                        MY_USER = User(
                            firebaseUser?.displayName.toString(),
                            firebaseUser?.uid.toString()
                        )
                    }

                    Handler(Looper.getMainLooper()).postDelayed({
                        Log.d("USER_FLUX", "-> registeractivity")
                        val intent = Intent(this@VerificationsActivity, RegisterActivity::class.java)
                        if (firebaseUser!!.displayName.isNullOrEmpty()){
                            intent.putExtra(NAME, userNameFromSignup)
                        }else {
                            intent.putExtra(NAME, firebaseUser.displayName)
                        }
                        startActivity(intent)
                        finish()
                    }, Constants.HANDLER_TIME_BRIDGE_2)


                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}