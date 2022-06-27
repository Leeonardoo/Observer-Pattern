package com.github.leeonardoo.observer.ui.observer

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.leeonardoo.observer.R
import com.github.leeonardoo.observer.databinding.ActivityObserverBinding
import com.github.leeonardoo.observer.model.Post
import com.github.leeonardoo.observer.pattern.Observer
import com.google.android.material.snackbar.Snackbar

class ObserverActivity : AppCompatActivity(R.layout.activity_observer) {

    private val binding by viewBinding(ActivityObserverBinding::bind, R.id.coordinator_layout)
    private val viewModel: ObserverViewModel by viewModels()

    private lateinit var postsObserver: Observer<List<Post>?>

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        setupActionBar()

        val adapter = PostAdapter(viewModel.posts.value ?: listOf())

        postsObserver = Observer { post ->
            adapter.updateList(post ?: listOf())
            binding
        }
        viewModel.posts.addObserver(postsObserver)
    }

    override fun onDestroy() {
        viewModel.posts.removeObserver(postsObserver)
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