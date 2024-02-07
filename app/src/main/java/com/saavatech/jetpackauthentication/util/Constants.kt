package com.saavatech.jetpackauthentication.util

import androidx.datastore.preferences.core.stringSetPreferencesKey

object Constants {
    const val BASE_URL = "https://regres.in/"
    const val AUTH_PREFERENCES = "AUTH_PREF"
    val AUTH_KEY = stringSetPreferencesKey("auth_key")
}