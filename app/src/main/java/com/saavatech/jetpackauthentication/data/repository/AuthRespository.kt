package com.saavatech.jetpackauthentication.data.repository

import com.saavatech.jetpackauthentication.data.local.AuthPreferences
import com.saavatech.jetpackauthentication.data.models.ApiService
import com.saavatech.jetpackauthentication.data.remote.request.AuthRequest
import com.saavatech.jetpackauthentication.data.remote.request.RegisterRequest
import com.saavatech.jetpackauthentication.domain.repository.AuthRepository
import com.saavatech.jetpackauthentication.util.Resource
import retrofit2.HttpException
import java.io.IOException

class AuthRespositoryImpl(
    private val apiService: ApiService,
    private val preferences: AuthPreferences
): AuthRepository {
    override suspend fun login(loginRequest: AuthRequest): Resource<Unit> {
        return try {
            val response = apiService.loginUser(loginRequest)

            println(response)
            preferences.saveAuthToken(response.token)
            Resource.Success(Unit)
        }catch (e: IOException){
            Resource.Error("${e.message}")
        }catch (e: HttpException){
            Resource.Error("${e.message}")
        }
    }

    override suspend fun register(registerRequest: RegisterRequest): Resource<Unit> {
        return try {
            val response = apiService.registerUser(registerRequest)
            preferences.saveAuthToken(response.token)
            Resource.Success(Unit)
        }catch (e: IOException){
            Resource.Error("${e.message}")
        }catch (e: HttpException){
            Resource.Error("${e.message}")
        }
    }

}