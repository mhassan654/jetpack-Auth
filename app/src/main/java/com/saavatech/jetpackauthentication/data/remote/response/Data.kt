package com.saavatech.jetpackauthentication.data.remote.response

data class Data(
    val body: String,
    val comments_count: Int,
    val created_at: String,
    val id: Int,
    val image: String,
    val likes: List<Any>,
    val likes_count: Int,
    val updated_at: String,
    val user: User,
    val user_id: Int
)