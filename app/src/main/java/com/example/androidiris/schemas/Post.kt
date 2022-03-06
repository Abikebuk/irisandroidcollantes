package com.example.androidiris.schemas

import android.os.Parcelable
import com.example.androidiris.schemas.Likes
import com.google.firebase.firestore.Exclude
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime
import java.util.*

@Parcelize
data class Post (
    @Exclude
    var id: String? = null,
    val userId: String? = null,
    val title: String? = null,
    val date: Date? = null,
    val text: String? = null,
    val image: String? = null,
    val video: String? = null,
    val likes: Likes? = null
) : Parcelable