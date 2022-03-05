package com.example.androidiris.schemas

import com.example.androidiris.schemas.Likes
import java.util.*

data class Post(
    val id: String? = null,
    val userId: String? = null,
    val title: String? = null,
    val date: Date? = null,
    val text: String? = null,
    val image: String? = null,
    val video: String? = null,
    val likes: Likes? = null
)