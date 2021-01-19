package com.example.marvelmyhero.register

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.VideoView
import com.example.marvelmyhero.R
import com.example.marvelmyhero.main.view.MainActivity
import com.example.marvelmyhero.utils.Constants.CONTEXT_RESQUEST_CODE
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {

    private var imageUri: Uri? = null

    private val userImage: ImageView by lazy { findViewById(R.id.image_userImage_register) }

    private val nickname: TextInputEditText by lazy { findViewById(R.id.editText_nickName_register) }

    private val name: TextInputEditText by lazy { findViewById(R.id.editText_name_register) }

    private val submitButton: Button by lazy { findViewById(R.id.btn_submit_register) }

    private val changeImageButton: ImageView by lazy { findViewById(R.id.image_editIcon_register) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        submitButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java ))
        }

        changeImageButton.setOnClickListener {
            findFile()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CONTEXT_RESQUEST_CODE && resultCode == RESULT_OK) {
            imageUri = data?.data
            userImage.setImageURI(imageUri)
        }
        
    }

    private fun findFile() {

        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, CONTEXT_RESQUEST_CODE)

    }
}