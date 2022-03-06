package com.example.androidiris

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
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
