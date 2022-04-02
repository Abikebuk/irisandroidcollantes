package com.example.androidiris

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidiris.auth.Authenticate
import com.example.androidiris.database.PostHandler
import com.example.androidiris.databinding.ActivityCreatePostBinding
import java.time.LocalDate
import java.util.*

class CreatePostActivity : AppCompatActivity() {
    private val currentUser = Authenticate.client.getCurrentUser()?.uid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCreatePostBinding.inflate(layoutInflater)
        binding.createPostButton.setOnClickListener {
            val title = binding.editTitle.text.toString()
            val content = binding.editContent.text.toString()
            if (currentUser != null) {
                PostHandler.create(
                    null,
                    currentUser,
                    title,
                    Date(),
                    content,
                    null,
                    null,
                    null
                    )
            }
            val intent = Intent(this@CreatePostActivity, HomeActivity::class.java)
            startActivity(intent)
        }
        setContentView(binding.root)
    }
}