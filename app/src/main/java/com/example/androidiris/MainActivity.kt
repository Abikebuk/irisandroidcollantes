package com.example.androidiris

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.androidiris.databinding.ActivityMainBinding
import com.example.androidiris.auth.Authenticate

class MainActivity : AppCompatActivity() {
    companion object{
        var auth : Authenticate =  Authenticate()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.HelloWorld.text = "ABFCs"
    }

    fun onClickToHomeButton(view: View){
        setContentView(R.layout.activity_home)
    }
}