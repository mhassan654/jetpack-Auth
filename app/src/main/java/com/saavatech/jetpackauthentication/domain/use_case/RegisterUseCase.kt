package com.saavatech.jetpackauthentication.domain.use_case

import com.saavatech.jetpackauthentication.data.remote.request.AuthRequest
import com.saavatech.jetpackauthentication.data.remote.request.RegisterRequest
import com.saavatech.jetpackauthentication.domain.model.AuthResult
import com.saavatech.jetpackauthentication.domain.model.RegisterResult
import com.saavatech.jetpackauthentication.domain.repository.AuthRepository
import timber.log.Timber
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        lastName: String,
        firstName: String,
        email:String,
        password:String,
        password_confirmation:String,
    ): RegisterResult {

        val emailError = if (email.isBlank()) "Email cannot be blank" else null
        val lastNameError = if (lastName.isBlank()) "Last Name cannot be blank" else null
        val firstNameError = if (firstName.isBlank()) "First Name cannot be blank" else null
        val passwordError = if (password.isBlank()) "Password cannot be blank" else null

        if (emailError != null){
            return RegisterResult(
                emailError = emailError
            )
        }

        if (lastNameError != null){
            return RegisterResult(
                lastNameError = lastNameError
            )
        }

        if (firstNameError != null){
            return RegisterResult(
                firstNameError = firstNameError
            )
        }

        if (passwordError!=null){
            return RegisterResult(
                passwordError = passwordError
            )
        }

        val registerRequest = RegisterRequest(
            lastName = lastName.trim(),
            firstName = firstName.trim(),
            email = email.trim(),
            password = password.trim(),
            password_confirmation = password.trim()
        )

        val  resp = RegisterResult(
            result = repository.register(registerRequest)
        )
//
        Timber.tag("register response message:").d(resp.result?.message) //resp.result?.message.toString()
//        Timber.tag("register response data:").d(resp.result?.data.toString())

        return resp
    }
}