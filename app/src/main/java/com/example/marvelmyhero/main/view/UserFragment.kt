package com.example.marvelmyhero.main.view

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelmyhero.R
import com.example.marvelmyhero.login.model.User
import com.example.marvelmyhero.utils.Constants
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso

class UserFragment(
    private val _user: User
) : Fragment() {

    private var imageUri: Uri? = null
    private lateinit var userImage: ImageView
    private val firebaseUser = FirebaseAuth.getInstance().currentUser
    private val storageRef = FirebaseStorage.getInstance().getReference(firebaseUser?.uid.toString())
    private val user = Firebase.auth.currentUser
    private lateinit var _view: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val frameLayout = view.findViewById<FrameLayout>(R.id.frameLayout_userFragment)
        val cardView = view.findViewById<MaterialCardView>(R.id.cardView_userFragment)
        val userImage = view.findViewById<ImageView>(R.id.img_user_fragmentUser)
        val userName = view.findViewById<TextView>(R.id.txt_userName_fragmentUser)
        val totalCard = view.findViewById<TextView>(R.id.txt_totalCards_fragmentUser)
        val btnImageChange = view.findViewById<ImageView>(R.id.imgEdit_UserFragment)

        _view = view

        cardView.setOnClickListener {
            val nothing = true
        }

        frameLayout.setOnClickListener {
            activity?.onBackPressed()
        }

        btnImageChange.setOnClickListener{
            findFile()
        }

        Picasso.get().load(_user.imageUrl).into(userImage)
        userName.text = _user.nickName
        totalCard.text = _user.deck.size.toString()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.CONTEXT_RESQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) {
            imageUri = data?.data
            userImage.setImageURI(imageUri)
            sendImage()
        }
    }

    private fun sendImage() {
        imageUri?.run {
            val extension = MimeTypeMap.getSingleton()
                .getExtensionFromMimeType(activity?.contentResolver?.getType(imageUri!!))
            storageRef.putFile(this)
                .addOnSuccessListener {
                    Log.d("FIREBASE_PIC", storageRef.toString())
                }
                .addOnFailureListener {
                    Log.d("FIREBASE_PIC",
                        "ERROR: Upload Picture!!",
                        )
                }
        }
    }
    private fun findFile() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, Constants.CONTEXT_RESQUEST_CODE)
    }

    private fun upDateProfile(){

        sendImage()

        val newName = view?.findViewById<TextInputEditText>(R.id.txt_userName_fragmentUser).text.toString()

        val profileUpdates = userProfileChangeRequest {
            displayName = newName
        }

        user!!.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(view.context, "Dados salvos com sucesso", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }
}