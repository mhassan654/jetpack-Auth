package com.saavatech.jetpackauthentication.domain.repository

import com.saavatech.jetpackauthentication.data.remote.request.PostRequest
import com.saavatech.jetpackauthentication.data.remote.response.PostsResponse
import com.saavatech.jetpackauthentication.util.Resource

interface PostRepository {
   suspend fun createPost(postsRequest: PostRequest): Resource<Unit>
   suspend fun fetchPosts(): List<PostsResponse>

}