package com.example.androidiris.schemas

import com.google.firebase.firestore.Exclude

data class User(
    @Exclude
    var id: String? = null,
    var firstname: String? = null,
    var lastname: String? = null,
    var mail: String? = null,
    var age: Int? = null,
    var phone: String? = null,
    var password: String? = null,
    var friends: List<String>? = null
)