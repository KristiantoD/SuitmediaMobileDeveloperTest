package com.example.suitmediamobiledevelopertest.view.listUser

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.suitmediamobiledevelopertest.R
import com.example.suitmediamobiledevelopertest.databinding.ActivityListUserBinding
import com.example.suitmediamobiledevelopertest.model.Preference
import com.example.suitmediamobiledevelopertest.view.ViewModelFactory
import com.example.suitmediamobiledevelopertest.view.adapter.ListUserAdapter
import com.example.suitmediamobiledevelopertest.view.adapter.LoadingStateAdapter

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class ListUserActivity : AppCompatActivity() {
    private lateinit var viewModel: ListUserViewModel
    private lateinit var binding: ActivityListUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListUserBinding.inflate(layoutInflater)
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
        )[ListUserViewModel::class.java]
    }

    private fun setupAction() {
        binding.rvUser.layoutManager = LinearLayoutManager(this)
        val listUserAdapter = ListUserAdapter { String -> viewModel.saveSelectedUser(String) }
        binding.rvUser.adapter = listUserAdapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                listUserAdapter.retry()
            }
        )
        viewModel.users.observe(this) {
            listUserAdapter.submitData(lifecycle, it)
        }
    }

}