package com.example.suitmediamobiledevelopertest.view.palindrome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.suitmediamobiledevelopertest.model.Preference
import kotlinx.coroutines.launch


class MainViewModel(private val pref: Preference) : ViewModel() {
    fun saveUser(user: String) {
        viewModelScope.launch {
            pref.saveUser(user)
        }
    }

    fun checkPalindrome(sentence: String): Boolean{
        var cleanSentence = ""
        for (i in sentence) {
            if (i.isLetterOrDigit()){
                cleanSentence+=i.lowercaseChar()
            }
        }

        var reverse = ""
        for(i in (cleanSentence.length - 1) downTo 0){
            reverse+=cleanSentence[i]
        }

        if(cleanSentence.equals(reverse)){
            return true
        }
        return false
    }
}