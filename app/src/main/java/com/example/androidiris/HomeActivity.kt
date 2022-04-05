package com.example.androidiris

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.androidiris.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        binding.newPostButton.setOnClickListener {
            val intent = Intent(this@HomeActivity, CreatePostActivity::class.java)
            Log.d("szdfgtfqergfdsfq","szdffdgdsqsdg")
            startActivity(intent)
        }
        setContentView(binding.root)
        changeView(ContentView.news)
    }

    fun changeView(view:ContentView){
        var fragment : Fragment? = null
        Log.d("AAAAZERGF", view.name)
        fragment = when (view){
            ContentView.friends -> FriendListFragment.newInstance("a","a")
            ContentView.news -> NewsFragment.newInstance("a","a")
            ContentView.profile -> ProfileFragment.newInstance("a","a")
            ContentView.chat -> ChatFragment.newInstance("a","a")
        }
        val tr = supportFragmentManager.beginTransaction()
        tr.replace(R.id.contentContainer, fragment)
        tr.commitAllowingStateLoss()
    }

    companion object{
        enum class ContentView{
            friends,
            news,
            profile,
            chat
        }
    }
}
