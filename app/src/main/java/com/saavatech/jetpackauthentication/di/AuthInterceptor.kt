package com.saavatech.jetpackauthentication.di

import com.saavatech.jetpackauthentication.data.local.AuthPreferences
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val authPreferences: AuthPreferences): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val authToken = runBlocking {
           authPreferences.getAuthToken()
        }
        // Modify the request as needed
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer  $authToken")
            .build()

        // Proceed with the modified request
        return chain.proceed(request)
    }
}