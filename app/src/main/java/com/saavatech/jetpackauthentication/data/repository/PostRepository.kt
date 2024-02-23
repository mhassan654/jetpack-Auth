package com.saavatech.jetpackauthentication.data.repository

import com.saavatech.jetpackauthentication.data.local.AuthPreferences
import com.saavatech.jetpackauthentication.data.models.ApiService
import com.saavatech.jetpackauthentication.data.remote.request.PostRequest
import com.saavatech.jetpackauthentication.data.remote.response.PostsDataResponse
import com.saavatech.jetpackauthentication.domain.repository.PostRepository
import com.saavatech.jetpackauthentication.util.Resource
import retrofit2.HttpException
import java.io.IOException

class PostRepositoryImpl(
    private val apiService: ApiService
): PostRepository {
    override suspend fun createPost(postsRequest: PostRequest): Resource<Unit> {
        TODO()
    }

    override suspend fun fetchPosts(): Resource<PostsDataResponse> {
        return try {
           val res= apiService.getPosts()
            Resource.Success(res)
        } catch (e: IOException) {
            Resource.Error("${e.message}")
        } catch (e: HttpException) {
            Resource.Error("${e.message}")
        }
    }
}