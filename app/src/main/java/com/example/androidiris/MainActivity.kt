package com.example.androidiris

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidiris.databinding.ActivityMainBinding
import com.example.androidiris.auth.Authenticate

class MainActivity : AppCompatActivity() {
    companion object{
        // var auth : Authenticate =  Authenticate()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.HelloWorld.text = "ABFCs"
    }
}