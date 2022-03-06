package com.example.androidiris.schemas

import android.os.Parcelable
import com.google.firebase.firestore.Exclude
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Comment (
    @Exclude
    val id: String? = null,
    val userId: String? = null,
    val date: Date? = null,
    val text: String? = null,
    val likes: Likes? = null,
    val answers: List<Comment>? = null
) : Parcelable