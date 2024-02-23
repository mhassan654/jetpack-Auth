package com.saavatech.jetpackauthentication.data.remote.request

import com.google.gson.annotations.SerializedName
import retrofit2.http.Headers

data class AuthRequest(

    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String
)


data class RegisterRequest(

    @SerializedName("email")  var email: String,
    @SerializedName("first_name")  var firstName: String,
    @SerializedName("last_name")  var lastName: String,
    @SerializedName("password")   var password: String,
    @SerializedName("password_confirmation")   var password_confirmation: String
)