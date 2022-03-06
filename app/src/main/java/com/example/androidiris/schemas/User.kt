package com.example.androidiris.schemas

import android.os.Parcelable
import com.google.firebase.firestore.Exclude
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var id: String? = null,
    var firstname: String? = null,
    var lastname: String? = null,
    var mail: String? = null,
    var age: Int? = null,
    var phone: String? = null,
    var password: String? = null,
    var friends: List<String>? = null
) : Parcelable