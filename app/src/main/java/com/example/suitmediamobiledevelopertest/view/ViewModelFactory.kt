package com.example.suitmediamobiledevelopertest.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.suitmediamobiledevelopertest.di.Injection
import com.example.suitmediamobiledevelopertest.model.Preference
import com.example.suitmediamobiledevelopertest.view.listUser.ListUserViewModel
import com.example.suitmediamobiledevelopertest.view.palindrome.MainViewModel
import com.example.suitmediamobiledevelopertest.view.welcome.WelcomeViewModel

class ViewModelFactory(private val pref: Preference, private val context: Context) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(pref) as T
            }
            modelClass.isAssignableFrom(WelcomeViewModel::class.java) -> {
                WelcomeViewModel(pref) as T
            }
            modelClass.isAssignableFrom(ListUserViewModel::class.java) -> {
                ListUserViewModel(pref, Injection.provideRepository(context)) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}