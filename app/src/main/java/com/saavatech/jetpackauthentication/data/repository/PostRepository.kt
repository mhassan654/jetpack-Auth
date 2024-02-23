package com.saavatech.jetpackauthentication.data.repository

import com.saavatech.jetpackauthentication.data.local.AuthPreferences
import com.saavatech.jetpackauthentication.data.models.ApiService
import com.saavatech.jetpackauthentication.data.remote.request.PostRequest
import com.saavatech.jetpackauthentication.data.remote.response.Data
import com.saavatech.jetpackauthentication.data.remote.response.PostsResponse
import com.saavatech.jetpackauthentication.data.remote.response.SampleData
import com.saavatech.jetpackauthentication.domain.repository.PostRepository
import com.saavatech.jetpackauthentication.util.Resource

class PostRepositoryImpl(
    private val apiService: ApiService,
    private val preferences: AuthPreferences
): PostRepository {
    override suspend fun createPost(postsRequest: PostRequest): Resource<Unit> {
        TODO()
    }

    override suspend fun fetchPosts(): List<Data> {
//        return try {
        return apiService.getPosts()

//        }catch (e: IOException){
//            Resource.Error("${e.message}")
//        }catch (e: HttpException){
//            Resource.Error("${e.message}")
//        }
    }
}