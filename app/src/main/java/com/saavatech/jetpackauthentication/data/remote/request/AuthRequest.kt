package com.saavatech.jetpackauthentication.data.remote.request

import com.google.gson.annotations.SerializedName
import retrofit2.http.Headers

data class AuthRequest(

    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String
)
