package com.saavatech.jetpackauthentication.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.saavatech.jetpackauthentication.common.TextFieldState
import com.saavatech.jetpackauthentication.common.UiEvents
import com.saavatech.jetpackauthentication.domain.use_case.LoginUseCase
import com.saavatech.jetpackauthentication.domain.use_case.RegisterUseCase
import com.saavatech.jetpackauthentication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
): ViewModel() {


    private val _eventsFlow = MutableSharedFlow<UiEvents>()
    val eventsFlow: SharedFlow<UiEvents> = _eventsFlow
//
//    // Function to trigger navigation
    private val _navigationEvents = MutableSharedFlow<UiEvents.NavigationEvent>()
    val navigationEvents: SharedFlow<UiEvents.NavigationEvent> = _navigationEvents

    private var _loginState  = mutableStateOf(AuthState())
    val loginState: State<AuthState> = _loginState

    private val  _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _firstName = mutableStateOf(TextFieldState())
    val firstName: State<TextFieldState> = _firstName

    fun setFirstName(value:String){
        _firstName.value = firstName.value.copy(text = value)
    }

    private val _lastName = mutableStateOf(TextFieldState())
    val lastName: State<TextFieldState> = _lastName

    fun setLastName(value:String){
        _lastName.value = lastName.value.copy(text = value)
    }

    private val _emailState = mutableStateOf(TextFieldState())
    val emailState: State<TextFieldState> = _emailState

    fun setEmail(value:String){
        _emailState.value = emailState.value.copy(text = value)
    }

    private val _passwordState = mutableStateOf(TextFieldState())
    val passwordState: State<TextFieldState> = _passwordState

    fun setPassword(value: String){
        _passwordState.value = passwordState.value.copy(text=value)
    }

    fun loginUser(){
        viewModelScope.launch {
            _loginState.value = loginState.value.copy(isLoading = true)

            val loginResult = loginUseCase(
                email = emailState.value.text,
                password = passwordState.value.text
            )

            _loginState.value = loginState.value.copy(isLoading = false)

            if (loginResult.emailError != null){
                _emailState.value=emailState.value.copy(error = loginResult.emailError)
            }
            if (loginResult.passwordError != null){
                _passwordState.value = passwordState.value.copy(error = loginResult.passwordError)
            }


            if (loginResult.result is Resource.Success) {
                Timber.tag("true").d("navigate home")
//                _eventFlow.emit(
//                    UiEvents.NavigationEvent("Home")//HomeScreenDestination.route
//                )
                _navigationEvents.emit(
                    UiEvents.NavigationEvent("Home")
                )
            }
            else if (loginResult.result is Resource.Error) {
                Timber.tag("false").d("display error")
                UiEvents.SnackbarEvent(
                    loginResult.result.message ?: "Error!"
                )
            }
        }
    }

    fun registerUser(){
        viewModelScope.launch {
            _loginState.value = loginState.value.copy(isLoading = false)

            val registerResult = registerUseCase(
                firstName = firstName.value.text,
                lastName = lastName.value.text,
                email = emailState.value.text,
                password = passwordState.value.text,
                password_confirmation = passwordState.value.text,
            )

            // Set lenient mode to true to accept malformed JSON
            _loginState.value = loginState.value.copy(isLoading = false)

            // set email error in state
            if (registerResult.emailError != null){
                _emailState.value=emailState.value.copy(error = registerResult.emailError)
            }

            // set first name state error
            if (registerResult.firstNameError != null){
                _firstName.value=firstName.value.copy(error = registerResult.firstNameError)
            }

            // set last name error
            if (registerResult.lastNameError != null){
                _lastName.value=lastName.value.copy(error = registerResult.lastNameError)
            }

            if (registerResult.passwordError != null){
                _passwordState.value = passwordState.value.copy(error = registerResult.passwordError)
            }

            Timber.tag("results data").d(registerResult.result?.message.toString())

            when(registerResult.result){
                is Resource.Success->{
                    _eventFlow.emit(
                        UiEvents.NavigationEvent("Home")//HomeScreenDestination.route
                    )
                }
                is Resource.Error->{
                    UiEvents.SnackbarEvent(
                        registerResult.result.message ?: "Error!"
                    )
                }
                else -> {
                    UiEvents.SnackbarEvent(
                        registerResult.result?.message ?: "Error!"
                    )
                }
            }
        }
    }

}

