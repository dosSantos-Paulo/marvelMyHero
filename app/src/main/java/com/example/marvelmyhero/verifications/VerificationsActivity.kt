package com.example.marvelmyhero.verifications

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.marvelmyhero.R
import com.example.marvelmyhero.main.view.MainActivity
import com.example.marvelmyhero.register.RegisterActivity
import com.example.marvelmyhero.utils.Constants
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verifications)

        isNewUser()

    }

    private fun isNewUser() {
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(RegisterActivity.DatabaseUser::class.java)
                Constants.IS_NEW_USER = value == null

                if (!Constants.IS_NEW_USER) {
                    storageRef.downloadUrl.addOnSuccessListener {
                        imageUri = it
                        Handler(Looper.getMainLooper()).postDelayed({
                            val intent =
                                Intent(this@VerificationsActivity, MainActivity::class.java)
                            intent.putExtra(Constants.IMAGE, imageUri.toString())
                            startActivity(intent)
                            finish()
                        }, Constants.HANDLER_TIME_BRIDGE_2)
                    }
                } else {
                    val intent = Intent(this@VerificationsActivity, RegisterActivity::class.java)
                    intent.putExtra(Constants.NAME, firebaseUser!!.displayName)
                    startActivity(intent)
                    finish()
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}