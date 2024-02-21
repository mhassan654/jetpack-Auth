package com.saavatech.jetpackauthentication.domain.model

import java.util.Date

data class Post(
    val id: Int,
    val userId: Int,
    val body: String,
    val image: String,
    val createdAt: Date,
    val updatedAt: Date,
    val commentsCount: Int,
    val likesCount: Int,
    val user: UserPost,
    val likes: List<Any>
)

data class UserPost(
    val id: Int,
    val name: String,
    val image: String?
)