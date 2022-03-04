package com.example.androidiris

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class LoginActivity : AppCompatActivity(),  View.OnClickListener {

    lateinit var buttonInscription : Button
    lateinit var buttonConnexion  : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        this.buttonInscription = findViewById<Button>(R.id.button2)
        this.buttonInscription.setOnClickListener(this)
        this.buttonConnexion = findViewById<Button>(R.id.button)
        this.buttonConnexion.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            /* R.id.buttonConnexion -> {
                 val i = Intent(this@MainActivity, FilActualite::class.java)
                 startActivity(i)
             }*/
            R.id.button2 -> {
                val i = Intent(this, SignUpActivity::class.java)
                startActivity(i)
            }
        }
    }
}