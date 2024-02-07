package com.saavatech.jetpackauthentication.common

sealed class UiEvents{
    data class SnackbarEvent(val message: String) : UiEvents()
    data class NavigationEvent(val route: String) : UiEvents()
}
