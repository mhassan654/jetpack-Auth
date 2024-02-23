package com.saavatech.jetpackauthentication.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.saavatech.jetpackauthentication.util.Constants.AUTH_KEY
import kotlinx.coroutines.flow.first

class AuthPreferences(private val dataStore: DataStore<Preferences>) {

    suspend fun saveAuthToken(loginToken:String){
        dataStore.edit { pref->
            pref[AUTH_KEY] = setOf(loginToken)
        }
    }

    /**
     * Get token saved from preferences
     */
    suspend fun getAuthToken(): String? {
        return dataStore.data.first()[AUTH_KEY]?.firstOrNull()
    }

}