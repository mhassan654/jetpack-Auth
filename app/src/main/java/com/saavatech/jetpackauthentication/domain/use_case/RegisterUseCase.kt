package com.saavatech.jetpackauthentication.domain.use_case

import com.saavatech.jetpackauthentication.data.remote.request.AuthRequest
import com.saavatech.jetpackauthentication.domain.model.AuthResult
import com.saavatech.jetpackauthentication.domain.repository.AuthRepository
import timber.log.Timber
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        email:String,
        password:String
    ): AuthResult {

        val emailError = if (email.isBlank()) "Username cannot be blank" else null
        val passwordError = if (password.isBlank()) "Password cannot be blank" else null

        if (emailError != null){
            return AuthResult(
                emailError = emailError
            )
        }

        if (passwordError!=null){
            return AuthResult(
                passwordError = passwordError
            )
        }

        val registerRequest = AuthRequest(
            email = email.trim(),
            password = password.trim()
        )

        Timber.tag("register request body:").d(registerRequest.toString())
        val  results = AuthResult(
            result = repository.register(registerRequest)
        )

        Timber.tag("register response:").d(results.result?.message.toString())

        return AuthResult(
            result = repository.register(registerRequest)
        )
    }
}