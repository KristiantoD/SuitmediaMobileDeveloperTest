package com.example.suitmediamobiledevelopertest.view.listUser

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.suitmediamobiledevelopertest.api.UserDataResponse
import com.example.suitmediamobiledevelopertest.model.Preference
import com.example.suitmediamobiledevelopertest.model.UserRepository
import kotlinx.coroutines.launch

class ListUserViewModel(private val pref: Preference, userRepository: UserRepository) :
    ViewModel() {
    var users: LiveData<PagingData<UserDataResponse>> =
        userRepository.getUser().cachedIn(viewModelScope)


    fun saveSelectedUser(selectedUser: String) {
        viewModelScope.launch {
            pref.saveSelectedUser(selectedUser)
        }
    }
}