package com.example.androidiris.schemas

import java.util.*

data class Comment (
    val userId: String? = null,
    val date: Date? = null,
    val text: String? = null,
    val likes: Likes? = null,
    val answers: List<Comment>? = null
)