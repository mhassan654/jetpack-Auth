package com.saavatech.jetpackauthentication.domain.repository

import com.saavatech.jetpackauthentication.data.remote.request.AuthRequest
import com.saavatech.jetpackauthentication.data.remote.request.RegisterRequest
import com.saavatech.jetpackauthentication.domain.model.RegisterResult
import com.saavatech.jetpackauthentication.util.Resource

interface AuthRepository {
    suspend fun login(loginRequest: AuthRequest): Resource<Unit>
    suspend fun register(registerRequest: RegisterRequest):Resource<Unit>
}