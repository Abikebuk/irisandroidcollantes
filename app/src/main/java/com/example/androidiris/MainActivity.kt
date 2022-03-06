package com.example.androidiris

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewStub
import com.example.androidiris.databinding.ActivityMainBinding
import com.example.androidiris.auth.Authenticate
import com.example.androidiris.database.PostHandler
import java.time.LocalDateTime
import java.util.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(binding.root)
        setContentView(R.layout.activity_main)

        // DEV
        /*
        PostHandler.create(
            null,
            "PgMIz4Ely7WluhcbhXGe",
            "title!",
            Date(),
            "ABCDEFG",
            null,
            null,
            null
        )*/
        //PostHandler.getAllFromUser("PgMIz4Ely7WluhcbhXGe")

    }

    fun onClickToHomeButton(view: View){
        val intent = Intent(this@MainActivity, HomeActivity::class.java)
        startActivity(intent)
    }



}