package com.example.suitmediamobiledevelopertest.view.welcome

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.suitmediamobiledevelopertest.R
import com.example.suitmediamobiledevelopertest.databinding.ActivityWelcomeBinding
import com.example.suitmediamobiledevelopertest.model.Preference
import com.example.suitmediamobiledevelopertest.view.ViewModelFactory
import com.example.suitmediamobiledevelopertest.view.listUser.ListUserActivity

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class WelcomeActivity : AppCompatActivity() {
    private lateinit var viewModel: WelcomeViewModel
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val toolbar: Toolbar = binding.toolbar
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        setupViewModel()
        setupAction()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(Preference.getInstance(dataStore), this)
        )[WelcomeViewModel::class.java]
    }

    private fun setupAction() {
        viewModel.getData().observe(this) {
            binding.tvUser.text = it.user
            binding.tvSelectedUser.text = it.selectedUser
        }

        binding.chooseButton.setOnClickListener {
            val intent = Intent(this, ListUserActivity::class.java)
            startActivity(intent)
        }
    }
}