package com.saavatech.jetpackauthentication.data.remote.response

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("token")   val token : String,
    @SerializedName("user")   val user : Unit
)
