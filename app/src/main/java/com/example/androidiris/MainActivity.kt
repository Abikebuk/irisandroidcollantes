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
import com.example.androidiris.database.UserHandler
import com.example.androidiris.database.UserQuery
import com.squareup.okhttp.Dispatcher
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import java.time.LocalDateTime
import java.util.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(binding.root)
        if (Authenticate.client.getCurrentUser()==null)
        {
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
        }else
        {
            val intent = Intent(this@MainActivity, HomeActivity::class.java)
            startActivity(intent)
        }
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
        /*
        PostHandler.getAllFromUser("PgMIz4Ely7WluhcbhXGe")
            .addOnSuccessListener { documents ->
                val docs = PostHandler.querySnapshotToPosts(documents)
                for ( d in docs ){
                    Log.d("TAGKZERGREG", d.toString())
                }
            }
         */
        //Authenticate.client.signInWithPassword()
        /*
        Authenticate.client.createNewUser(
            "c@de.fr",
            "azerty",
            "a",
            "b",
            123,
            "c",
        )
         */
        UserHandler.addFriend("Bz7MWLrjTRdkespEGu4ySb2GqQy1", "hTJ9EKac5IOp8fm0rbZFOuujTdi2")
    }

    fun onClickToHomeButton(view: View){
        val intent = Intent(this@MainActivity, HomeActivity::class.java)
        startActivity(intent)
    }


}