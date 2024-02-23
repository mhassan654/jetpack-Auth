package com.saavatech.jetpackauthentication.data.remote.response

import com.google.gson.annotations.SerializedName
import com.saavatech.jetpackauthentication.domain.model.UserPost

data class PostsDataResponse(
    @SerializedName("data")  val data : List<PostsResponse>? = null,
    @SerializedName("message") val message: String? = null
)

data class PostsResponse(
    @SerializedName("id")  var id: Int,
    @SerializedName("user_id")  var userId: Int,
    @SerializedName("body")  var body: String,
    @SerializedName("image")   var image: String,
    @SerializedName("created_at")   var createdAt: String,
    @SerializedName("comments_count")   var commentsCount: Int,
    @SerializedName("likes_count")   var likesCount: Int,
    @SerializedName("user")   var user: UserPost,
    @SerializedName("likes")   var likes: Any,
)