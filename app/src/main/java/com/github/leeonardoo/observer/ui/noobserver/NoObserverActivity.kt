package com.github.leeonardoo.observer.ui.noobserver

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.leeonardoo.observer.R
import com.github.leeonardoo.observer.databinding.ActivityNoObserverBinding
import com.github.leeonardoo.observer.util.applyNavbarInsets

class NoObserverActivity : AppCompatActivity(R.layout.activity_no_observer) {

    private val binding by viewBinding(ActivityNoObserverBinding::bind, R.id.coordinator_layout)
    private val viewModel: NoObserverViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setupActionBar()

        val adapter = PostAdapter(this, viewModel.posts) {
            viewModel.removePost(it)
        }

        binding.listView.adapter = adapter
        binding.listView.applyNavbarInsets()

        viewModel.postChangeCallback = PostChangeCallback {
            val newAdapter = PostAdapter(this, viewModel.posts) {
                viewModel.removePost(it)
            }

            binding.listView.adapter = newAdapter
        }

        binding.fab.setOnClickListener {
            viewModel.addRandomPost()
        }
    }

    override fun onDestroy() {
        viewModel.postChangeCallback = null
        super.onDestroy()
    }

    private fun setupActionBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbar.setNavigationOnClickListener {
            super.onBackPressed()
        }

        binding.toolbar.title = getString(R.string.app_name)
    }
}