package com.example.androidiris

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewStub
import com.example.androidiris.databinding.ActivityMainBinding
import com.example.androidiris.auth.Authenticate

class MainActivity : AppCompatActivity() {
    companion object{
        var auth : Authenticate =  Authenticate()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(binding.root)
        setContentView(R.layout.activity_main)
        binding.HelloWorld.setText("aaaaa")
    }

    fun onClickToHomeButton(view: View){
        val intent = Intent(this@MainActivity, HomeActivity::class.java)
        startActivity(intent)
    }



}