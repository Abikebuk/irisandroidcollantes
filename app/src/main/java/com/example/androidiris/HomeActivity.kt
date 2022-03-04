package com.example.androidiris

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    /*class Posts : AppCompatActivity(), View.OnClickListener{
    var post =  Post("ramos",
        "Test 1",
        Calendar.getInstance().time,
        "Un simple test",
        null,
        null,
        Likes(listOf("5")))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)
        Log.d("testText", "Create")
    }

    override fun onClick(p0: View?) {
        var testText = findViewById<TextView>(R.id.testText)
        testText.setText("Test 2")
        Log.d("testText", "Fonctionne")
    }
}*/
}
