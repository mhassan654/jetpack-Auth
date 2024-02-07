package com.saavatech.jetpackauthentication.presentation.register

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.saavatech.jetpackauthentication.Destinations
import com.saavatech.jetpackauthentication.DestinationsNavigator
import com.saavatech.jetpackauthentication.common.UiEvents
import com.saavatech.jetpackauthentication.presentation.AuthViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RegisterScreen(
    navController: DestinationsNavigator,
    viewModel: AuthViewModel = hiltViewModel()
){
    val emailState = viewModel.emailState.value
    val passwordState = viewModel.passwordState.value
    val firstNameState = viewModel.firstName.value
    val lastNameState = viewModel.lastName.value
    val state = viewModel.loginState.value
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvents.SnackbarEvent -> {
                    snackbarHostState.showSnackbar(
                        message = event.message,
                        duration = SnackbarDuration.Short
                    )
                }
                is UiEvents.NavigationEvent -> {
                    navController.navigateTo(Destinations.Login)
                    snackbarHostState.showSnackbar(
                        message = "Register Successful",
                        duration = SnackbarDuration.Short
                    )
                }

                else -> {}
            }
        }
    }
}