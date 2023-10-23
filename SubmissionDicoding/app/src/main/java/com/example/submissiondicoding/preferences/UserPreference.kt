package com.example.submissiondicoding.preferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreference private constructor(private val dataStore: DataStore<Preferences>) {
    suspend fun saveToken(token: String) {
        dataStore.edit { pref ->
            pref[TOKEN_PREF] = token
        }
    }

    suspend fun discardToken() {
        dataStore.edit { pref ->
            pref[TOKEN_PREF] = "none"
        }
    }

    fun getToken(): Flow<String> {
        return dataStore.data.map { token ->
            val tokenLogin = token[TOKEN_PREF] ?: "none"
            tokenLogin
        }
    }

    suspend fun login() {
        dataStore.edit { preferences ->
            preferences[STATE_KEY] = true
        }
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences[STATE_KEY] = false
        }
    }

    fun readState(): Flow<Boolean> {
        return dataStore.data.map { pref ->
            pref[STATE_KEY] ?: false
        }
    }


    companion object {

        @Volatile
        private var INSTANCE: UserPreference? = null

        private val STATE_KEY = booleanPreferencesKey("state")
        private val TOKEN_PREF = stringPreferencesKey("token_preference")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}