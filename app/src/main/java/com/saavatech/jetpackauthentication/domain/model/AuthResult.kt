package com.saavatech.jetpackauthentication.domain.model

import com.saavatech.jetpackauthentication.util.Resource

data class AuthResult(
    val passwordError: String? = null,
    val emailError : String? = null,
    val result: Resource<Unit>? = null
)
