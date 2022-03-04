package com.example.androidiris

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton

class SignUpActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var returnBouton  : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        this.returnBouton = findViewById<ImageButton>(R.id.returnButton)
        this.returnBouton.setOnClickListener(this)
    }

    fun returnFunction(view: View) {
        when (view.id) {
            R.id.returnButton -> {
                val i = Intent(this, MainActivity::class.java)
                startActivity(i);
            }
        }
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}