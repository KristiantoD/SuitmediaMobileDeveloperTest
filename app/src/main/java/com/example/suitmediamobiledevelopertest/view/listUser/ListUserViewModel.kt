package com.example.suitmediamobiledevelopertest.view.listUser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.suitmediamobiledevelopertest.model.Preference
import kotlinx.coroutines.launch

class ListUserViewModel (private val pref: Preference) : ViewModel() {
    fun saveSelectedUser(selectedUser: String) {
        viewModelScope.launch {
            pref.saveSelectedUser(selectedUser)
        }
    }
}