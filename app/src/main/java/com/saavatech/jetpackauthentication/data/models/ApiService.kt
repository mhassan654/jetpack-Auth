package com.saavatech.jetpackauthentication.data.models

import com.saavatech.jetpackauthentication.data.remote.request.AuthRequest
import com.saavatech.jetpackauthentication.data.remote.request.PostRequest
import com.saavatech.jetpackauthentication.data.remote.request.RegisterRequest
import com.saavatech.jetpackauthentication.data.remote.response.AuthResponse
import com.saavatech.jetpackauthentication.data.remote.response.PostsDataResponse
import com.saavatech.jetpackauthentication.data.remote.response.PostsResponse
import com.saavatech.jetpackauthentication.data.remote.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @Headers("Accept:application/json")
    @POST("/api/login")
    suspend fun loginUser(
        @Body loginRequest: AuthRequest
    ) : AuthResponse



    @Headers("Accept:application/json")
    @POST("/api/register")
    suspend fun registerUser(
        @Body registerRequest: RegisterRequest
    ) : RegisterResponse

    @Headers("Accept:application/json")
    @POST("/api/posts")
    suspend fun addPost(
        @Body postRequest: PostRequest
    ) : PostsResponse

    @GET("/api/user/{id}")
    suspend fun getUserDetails()

    @Headers("Accept:application/json")
    @GET("/api/posts")
    suspend fun getPosts(): PostsDataResponse
}