package com.saavatech.jetpackauthentication.data.models

import com.saavatech.jetpackauthentication.data.remote.request.AuthRequest
import com.saavatech.jetpackauthentication.data.remote.response.AuthResponse
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @Headers("Accept:application/json")
    @POST("api/login")
    suspend fun loginUser(
        @Body loginRequest: AuthRequest
    ) : AuthResponse



    @Headers("Accept:application/json")
    @POST("api/register")
    suspend fun registerUser(
        @Body registerRequest: AuthRequest
    ) : AuthResponse

    @GET("api/user/{id}")
    suspend fun getUserDetails()
}