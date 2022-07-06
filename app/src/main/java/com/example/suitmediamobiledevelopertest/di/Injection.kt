package com.example.suitmediamobiledevelopertest.di

import android.content.Context
import com.example.suitmediamobiledevelopertest.api.ApiConfig
import com.example.suitmediamobiledevelopertest.model.UserDatabase
import com.example.suitmediamobiledevelopertest.model.UserRepository

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val database = UserDatabase.getDatabase(context)
        val apiService = ApiConfig.getApiService()
        return UserRepository(database, apiService)
    }
}