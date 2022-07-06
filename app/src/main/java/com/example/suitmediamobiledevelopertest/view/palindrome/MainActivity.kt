package com.example.suitmediamobiledevelopertest.view.palindrome

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.suitmediamobiledevelopertest.R
import com.example.suitmediamobiledevelopertest.databinding.ActivityMainBinding
import com.example.suitmediamobiledevelopertest.model.Preference
import com.example.suitmediamobiledevelopertest.view.ViewModelFactory
import com.example.suitmediamobiledevelopertest.view.welcome.WelcomeActivity

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        setupViewModel()
        setupAction()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(Preference.getInstance(dataStore))
        )[MainViewModel::class.java]
    }

    private fun setupAction() {
        binding.nextButton.setOnClickListener {
            val user = binding.userEditText.text.toString()
            if (user.isNullOrBlank()){
                    binding.userEditTextLayout.error = getString(R.string.insert_name)
            } else {
                viewModel.saveUser(user)
                val intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)
            }
        }

        binding.checkButton.setOnClickListener {
            val sentence = binding.palindromEditText.text.toString()
            val palindrome = viewModel.checkPalindrome(sentence)
            var text = ""
            text = if (palindrome) {
                getString(R.string.is_palindrome)
            } else {
                getString(R.string.not_palindrome)
            }
            Toast.makeText(
                this,
                text,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}