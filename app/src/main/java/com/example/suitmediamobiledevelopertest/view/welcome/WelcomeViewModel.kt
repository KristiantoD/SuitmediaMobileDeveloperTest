package com.example.suitmediamobiledevelopertest.view.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.suitmediamobiledevelopertest.model.Preference
import com.example.suitmediamobiledevelopertest.model.UserModel

class WelcomeViewModel(private val pref: Preference) : ViewModel() {
    fun getData(): LiveData<UserModel> {
        return pref.getData().asLiveData()
    }
}