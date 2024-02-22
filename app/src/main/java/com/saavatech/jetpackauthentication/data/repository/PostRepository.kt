package com.saavatech.jetpackauthentication.data.repository

import com.saavatech.jetpackauthentication.data.local.AuthPreferences
import com.saavatech.jetpackauthentication.data.models.ApiService
import com.saavatech.jetpackauthentication.data.remote.request.PostRequest
import com.saavatech.jetpackauthentication.data.remote.response.PostsResponse
import com.saavatech.jetpackauthentication.domain.repository.PostRepository
import com.saavatech.jetpackauthentication.util.Resource
import retrofit2.HttpException
import java.io.IOException

class PostRepositoryImpl(
    private val apiService: ApiService,
    private val preferences: AuthPreferences
): PostRepository {
    override suspend fun createPost(postsRequest: PostRequest): Resource<Unit> {
        TODO()
    }

    override suspend fun fetchPosts(): Resource<Unit> {
        return try {
            val response = apiService.getPosts()

            Resource.Success(Unit)
        }catch (e: IOException){
            Resource.Error("${e.message}")
        }catch (e: HttpException){
            Resource.Error("${e.message}")
        }
    }
}