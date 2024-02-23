package com.saavatech.jetpackauthentication.domain.model

import com.saavatech.jetpackauthentication.data.remote.response.PostsDataResponse
import com.saavatech.jetpackauthentication.util.Resource

data class AuthResult(
    val passwordError: String? = null,
    val emailError : String? = null,
    val result: Resource<Unit>? = null
)

data class RegisterResult(
    val passwordError: String? = null,
    val emailError : String? = null,
    val firstNameError : String? = null,
    val lastNameError : String? = null,
    val result: Resource<Unit>? = null
)

data class PostsResult(
    val result: Resource<PostsDataResponse>? =null
)

