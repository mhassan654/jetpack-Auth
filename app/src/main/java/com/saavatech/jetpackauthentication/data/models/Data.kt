package com.saavatech.jetpackauthentication.data.models

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("id") val id: Int? =null,
    @SerializedName("email") val email: String? =null,
    @SerializedName("firstName") val first_name: String? =null,
    @SerializedName("lastName") val last_name: Int? =null,
    @SerializedName("avatar") val avatar: String? =null,
)
