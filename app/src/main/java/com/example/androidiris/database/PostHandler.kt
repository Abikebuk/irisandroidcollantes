package com.example.androidiris.database

import android.util.Log
import com.example.androidiris.schemas.Likes
import com.example.androidiris.schemas.Post
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class PostHandler {
    companion object{
        var TAG = "PostHandler"
        var dbName = "posts"

        fun create(
            id: String,
            userId: String,
            title: String? = null,
            date: Date,
            text: String,
            image: String? = null,
            video: String? = null,
            likes: Likes? = null
        ) : DocumentReference? {
            val post = Post(id, userId, title, date, text, image, video, likes)
            var res: DocumentReference? = null
            Firebase.firestore.collection(dbName).add(post)
                .addOnSuccessListener { docRef ->
                    Log.d(TAG, "Post created with Id : ${docRef.id}")
                    res = docRef
                }.addOnFailureListener { e ->
                    Log.d(TAG, "Error creating a new post", e)
                }
            return res
        }

        /**
        fun get(postId : String): Post?{
            val docRef = Firebase.firestore.collection(dbName).where
        }
        */
    }
}