package com.example.androidiris.database

import android.util.Log
import com.example.androidiris.schemas.Likes
import com.example.androidiris.schemas.Post
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

class PostHandler {
    companion object{
        var TAG = "PostHandler"
        var dbName = "posts"

        fun create(
            id: String? = null,
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


        fun getAllFromUser(userId : String): Task<QuerySnapshot> {
            val docRef = Firebase.firestore.collection(dbName).whereEqualTo("userId", userId).get()
            return docRef
        }

        suspend fun getAllFromUserAndFriend(userId: String): List<Post>{
            val res = ArrayList<Post>()
            res.addAll(querySnapshotToPosts(Tasks.await(getAllFromUser(userId))))
            val user = UserHandler.documentSnapshotToDocument(Tasks.await(UserHandler.get(userId)))
            if (user != null) {
                for(friend in user.friends!!) {
                    res.addAll(querySnapshotToPosts(Tasks.await(getAllFromUser(friend))))
                }
            }
            return res.sortedWith(compareByDescending { it.date });
        }

        fun querySnapshotToPosts (querySnapshot: QuerySnapshot): ArrayList<Post> {
            var res : ArrayList<Post> = ArrayList()
            for (doc in querySnapshot){
                val post = doc.toObject<Post>()
                post.id = doc.id
                Log.d(TAG, post.toString())
                res += post
            }
            return res;
        }
    }
}