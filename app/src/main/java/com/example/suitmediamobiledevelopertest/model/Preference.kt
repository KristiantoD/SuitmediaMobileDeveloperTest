package com.example.suitmediamobiledevelopertest.model

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class Preference private constructor(private val dataStore: DataStore<Preferences>) {

    fun getData(): Flow<UserModel> {
        return dataStore.data.map { preferences ->
            UserModel(
                preferences[USER_KEY] ?: "",
                preferences[SELECTED_USER_KEY] ?: "Select a User first"
            )
        }
    }

    suspend fun saveUser(user: String) {
        dataStore.edit { preferences ->
            preferences[USER_KEY] = user
        }
    }

    suspend fun saveSelectedUser(selectedUser: String) {
        dataStore.edit { preferences ->
            preferences[SELECTED_USER_KEY] = selectedUser
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: Preference? = null

        private val USER_KEY = stringPreferencesKey("user")
        private val SELECTED_USER_KEY = stringPreferencesKey("selected_user")

        fun getInstance(dataStore: DataStore<Preferences>): Preference {
            return INSTANCE ?: synchronized(this) {
                val instance = Preference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}

data class UserModel(
    val user: String,
    val selectedUser: String
)