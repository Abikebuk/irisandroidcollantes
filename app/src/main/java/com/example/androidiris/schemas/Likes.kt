package com.example.androidiris.schemas

import com.google.firebase.firestore.Exclude

data class Likes(
    @Exclude
    val id: String? = null,
    val users: List<String>? = null
    )