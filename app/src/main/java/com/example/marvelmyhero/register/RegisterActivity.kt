package com.example.marvelmyhero.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.marvelmyhero.R
import com.example.marvelmyhero.main.view.MainActivity
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val nickName = findViewById<TextInputEditText>(R.id.editText_nickName_register)
        val name = findViewById<TextInputEditText>(R.id.editText_name_register)
        val submitButton = findViewById<Button>(R.id.btn_submit_register)

        val changeImageButton = findViewById<ImageView>(R.id.image_editIcon_register)

        submitButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java )
            startActivity(intent)
        }

        changeImageButton.setOnClickListener {
            Toast.makeText(this, "try", Toast.LENGTH_LONG).show()
        }
    }
}