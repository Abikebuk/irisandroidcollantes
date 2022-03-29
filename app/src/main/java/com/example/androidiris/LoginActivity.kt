package com.example.androidiris

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.androidiris.auth.Authenticate
import com.example.androidiris.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {


    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        Log.d("jdh", "start")

        binding.buttonConnection.setOnClickListener { view ->
            onClickConnection(view)
        }

        binding.buttonSignup.setOnClickListener { view ->
            val i = Intent(this, SignUpActivity::class.java)
            startActivity(i) }

        setContentView(binding.root)

    }

     fun onClickConnection(v: View) {
         Log.d("jdh", binding.mailConnection.text.toString())
         Log.d("jdh", "zrifgjerziogjzerg")

         val mail = binding.mailConnection.text.toString()
         val psw = binding.pswConnection.text.toString()

         Authenticate.client.signInWithPassword(mail, psw)
             .addOnSuccessListener {
                 val currentUser = Authenticate.client.getCurrentUser()
                 Log.d("jdh",currentUser.toString())

                 if (currentUser!=null){
                     val i = Intent(this, HomeActivity::class.java)
                     startActivity(i)
                 }
             }

    }
/*
    fun connectionUser(){
        var email = mail.getText().toString().trim()
        var pwd = mdp.getText().toString().trim()


        if(mail==null){
            mail.setError("Un mail est requis !")
            mail.requestFocus()
            return
        }
        if(mdp==null){
            mdp.setError("Un mail est requis !")
            mdp.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mail.setError("Mail invalide !")
            mail.requestFocus()
            return
        }



    }
    */
}