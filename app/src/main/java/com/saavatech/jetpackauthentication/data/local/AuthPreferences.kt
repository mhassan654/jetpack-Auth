package com.saavatech.jetpackauthentication.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.saavatech.jetpackauthentication.util.Constants.AUTH_KEY

class AuthPreferences(private val dataStore: DataStore<Preferences>) {

    suspend fun saveAuthToken(loginToken:String){
        dataStore.edit { pref->
            pref[AUTH_KEY] = setOf(loginToken)
        }
    }
}