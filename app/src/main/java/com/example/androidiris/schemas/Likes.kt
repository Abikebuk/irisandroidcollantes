package com.example.androidiris.schemas

import android.os.Parcelable
import com.google.firebase.firestore.Exclude
import kotlinx.parcelize.Parcelize

@Parcelize
data class Likes(
    @Exclude @JvmField
    val id: String? = null,
    val users: List<String>? = null
    ) : Parcelable