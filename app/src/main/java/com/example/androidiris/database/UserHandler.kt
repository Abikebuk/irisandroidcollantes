package com.example.androidiris.database

import android.util.Log
import com.example.androidiris.schemas.User
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.*
import com.google.firebase.ktx.Firebase

class UserHandler {
    companion object {
        var TAG = "UserHandler"
        var dbName = "users"

        fun create(
            id: String,
            email: String,
            firstname: String,
            lastname: String,
            age: Int,
            phone: String
        ): DocumentReference? {
            val user = User(null, firstname, lastname, email, age, phone)
            var res: DocumentReference? = null
            Firebase.firestore.collection(dbName).document(id).set(user)
                .addOnSuccessListener { docRef ->
                    Log.d(TAG, "User created with ID : ${id}")
                }.addOnFailureListener { e ->
                    Log.d(TAG, "Error creating a new user.", e)
                }
            return res
        }

        fun get(userId: String): Task<DocumentSnapshot> {
            val docRef = Firebase.firestore.collection(dbName).document(userId)
            return docRef.get()
        }

        fun update(user: User) {
            val docRef = Firebase.firestore.collection(dbName).document(user.mail!!).set(user);
        }

        fun addFriend(userId: String, friendId: String) {
            Log.d(TAG, "Adding friend ${friendId} to ${userId}")
            val docRef = Firebase.firestore.collection(dbName).document(userId)
                .update("friends", FieldValue.arrayUnion(friendId))
        }

        fun removeFriend(userId: String, friendId: String){
            Log.d(TAG, "Removing friend ${friendId} to ${userId}")
            val docRef = Firebase.firestore.collection(dbName).document(userId)
                .update("friends", FieldValue.arrayRemove(friendId))
        }

        fun searchFriend(query : String){
            query.split(' ')
        }

        fun documentSnapshotToDocument(documentSnapshot : DocumentSnapshot): User? {
            val user = documentSnapshot.toObject<User>()
            user?.id = documentSnapshot.id
            return documentSnapshot.toObject<User>()
        }
        fun documentSnapshotToDocument(querySnapshot : QuerySnapshot): List<User> {
            val users = ArrayList<User>()
            for(user in querySnapshot){
                val u = user.toObject<User>()
                u.id = user.id
                users.add(u)
            }
            return users.toList()
        }
    }
}