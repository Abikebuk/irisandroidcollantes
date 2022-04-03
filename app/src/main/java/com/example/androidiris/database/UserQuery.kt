package com.example.androidiris.database

import android.annotation.SuppressLint
import android.util.Log
import com.example.androidiris.schemas.User
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class UserQuery (query: String){
    private val query = query;
    private var state : Int = 0
    private var maxState = 0
    private val results : ArrayList<WeightedResult> = ArrayList()


    data class WeightedResult (val _user : User) {
        val user: User = _user
        var weight: Int = 0
    }

    private fun addUser (user: User) {
        val indexExistingUser = existUser(user)
        if ( indexExistingUser >= 0){
            results[indexExistingUser].weight += 1
        }else {
            results.add(WeightedResult(user))
        }
        Log.d("EFIOZEFI", results.size.toString())
    }

    @SuppressLint("RestrictedApi")
    fun existUser(user : User): Int {
        for ( i in 0 until results.size){
            if (user.mail == results[i].user.mail){
                return i
            }
        }
        return -1
    }

    private fun executeQuery(field: String, word: String): Task<QuerySnapshot> {
        Log.d("EFIOZEFI", "${field} : ${word}")
        return Firebase.firestore.collection(UserHandler.dbName).whereEqualTo(field, word).get()
            .addOnSuccessListener { querySnapshot ->
                Log.d("EFIOZEFI", querySnapshot.documents.toString())
                val users = UserHandler.documentSnapshotToDocument(querySnapshot)
                for (user in users){
                    addUser(user)
                }
                state+=1
            }.addOnFailureListener{
                state+=1
            }
    }

    suspend fun get(): ArrayList<WeightedResult> {
        val words = query.split(" ")
        val async = GlobalScope.async{
            withContext(Dispatchers.Default){
                for ( word in words ){
                    Tasks.await(executeQuery("lastname", word), 2, TimeUnit.SECONDS)
                    Tasks.await(executeQuery("firstname", word), 2, TimeUnit.SECONDS)
                }
            }
        }
        results.sortByDescending { it.weight }
        async.await()
        return results;
    }

    fun printResult(){
        Log.d("EFIOZEFI", "printing ${results.size} ${state}")
        for (r in results){
            Log.d("EFIOZEFI", "${r.weight} : ${r._user.lastname} ${r._user.firstname} ${r._user.mail}")
        }
    }
}