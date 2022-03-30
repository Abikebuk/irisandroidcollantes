package com.example.androidiris

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.androidiris.auth.Authenticate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MenuFragment : Fragment() , View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu, container, false)
        view.findViewById<Button>(R.id.MenuFriendsButton).setOnClickListener(this)
        view.findViewById<Button>(R.id.MenuHomeButton).setOnClickListener(this)
        view.findViewById<Button>(R.id.MenuChatButton).setOnClickListener(this)
        view.findViewById<Button>(R.id.MenuProfileButton).setOnClickListener(this)
        return view
    }

    override fun onClick(p0: View?) {
        if (p0 != null) {
            val parent = activity as HomeActivity;
            when (p0.id){
                R.id.MenuFriendsButton -> parent.changeView(HomeActivity.Companion.ContentView.friends)
                R.id.MenuHomeButton -> parent.changeView(HomeActivity.Companion.ContentView.news)
                R.id.MenuProfileButton ->  parent.changeView(HomeActivity.Companion.ContentView.profile)
                R.id.MenuChatButton -> {
                    Authenticate.client.signOut()
                    val i = Intent(activity, LoginActivity::class.java)
                    startActivity(i) }
                }
            }
        }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MenuFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }}