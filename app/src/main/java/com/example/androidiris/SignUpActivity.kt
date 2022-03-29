package com.example.androidiris

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.ImageButton
import com.example.androidiris.auth.Authenticate
import com.example.androidiris.databinding.ActivityLoginBinding
import com.example.androidiris.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)

        binding.buttonSignin.setOnClickListener { view ->
            onClickConnection(view)
        }

        setContentView(binding.root)

    }

    fun onClickConnection(v: View) {
        Log.d("jdh", "test")

        val name = binding.nameSignin.text.toString()
        val surname = binding.surnameSignin.text.toString()
        val mail = binding.mailSignin.text.toString()
        val psw = binding.pswSignin.text.toString()
        val age = binding.birthSignin.text.toString().toInt()
        val phone = "0102030405"

        Authenticate.client.createNewUser(mail, psw, name, surname,age,phone)
            .addOnSuccessListener {
                val currentUser = Authenticate.client.getCurrentUser()
                Log.d("jdh",currentUser.toString())

                if (currentUser!=null){
                    val i = Intent(this, HomeActivity::class.java)
                    startActivity(i)
                }
            }

    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}


