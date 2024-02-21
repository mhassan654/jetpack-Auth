package com.saavatech.jetpackauthentication.data.repository

import com.saavatech.jetpackauthentication.data.local.AuthPreferences
import com.saavatech.jetpackauthentication.data.models.ApiService
import com.saavatech.jetpackauthentication.data.remote.request.PostRequest
import com.saavatech.jetpackauthentication.data.remote.response.PostsResponse
import com.saavatech.jetpackauthentication.domain.repository.PostRepository
import com.saavatech.jetpackauthentication.util.Resource

class PostRepositoryImpl(
    private val apiService: ApiService,
    private val preferences: AuthPreferences
): PostRepository {
    override suspend fun createPost(postsRequest: PostRequest): Resource<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchPosts(postsResponse: PostsResponse): Resource<Unit> {
        TODO("Not yet implemented")
    }
}