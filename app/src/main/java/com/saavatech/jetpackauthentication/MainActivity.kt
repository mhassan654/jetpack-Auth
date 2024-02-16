package com.saavatech.jetpackauthentication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.saavatech.jetpackauthentication.common.UiEvents
import com.saavatech.jetpackauthentication.enums.MainRoute
import com.saavatech.jetpackauthentication.presentation.AuthViewModel
import com.saavatech.jetpackauthentication.presentation.login.LoginScreen
import com.saavatech.jetpackauthentication.presentation.register.RegisterScreen
import com.saavatech.jetpackauthentication.presentation.welcome.WelcomeScreen
import com.saavatech.jetpackauthentication.ui.theme.JetPackAuthenticationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackAuthenticationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    DefaultNavigation()
                }
            }
        }
    }
}

@Composable
fun DefaultNavigation(){
   val navController: NavHostController = rememberNavController()
    val destinationsNavigator = DestinationsNavigator(navController)

    // Observe navigation events
    val authViewModel: AuthViewModel = hiltViewModel()
    LaunchedEffect(key1 = authViewModel.navigationEvents) {
        authViewModel.navigationEvents.collect { event ->
            when (event) {
                is UiEvents.NavigationEvent -> {
                    // Handle navigation event
                    navController.navigate(event.route)
                }
                // Add other navigation event handling if needed
            }
        }
    }

    NavHost(navController = navController, startDestination = Destinations.Login.route) {
        composable(Destinations.Login.route) {
            LoginScreen(destinationsNavigator)
        }

        composable(Destinations.Register.route){
            WelcomeScreen(destinationsNavigator)
        }

        composable(Destinations.Home.route){
            HomeScreen()
        }

        composable(MainRoute.Register.name){
            RegisterScreen(destinationsNavigator)
        }
        // Add other destinations here if needed
    }

}


class DestinationsNavigator(private val navController: NavHostController) {
    fun navigateTo(destination: Destinations) {
        navController.navigate(destination.route)
    }

    fun navigateUp() {
        navController.popBackStack()
    }
}

sealed class Destinations(val route: String) {
    data object Login : Destinations("login")
    data object Register : Destinations("Register")
    data object Home : Destinations("Home")
    // Define other destinations here
}