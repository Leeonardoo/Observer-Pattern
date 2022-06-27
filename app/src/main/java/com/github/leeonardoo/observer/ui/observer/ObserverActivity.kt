package com.github.leeonardoo.observer.ui.observer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.github.leeonardoo.observer.R
import com.github.leeonardoo.observer.databinding.ActivityObserverBinding
import com.google.android.material.snackbar.Snackbar

class ObserverActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityObserverBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityObserverBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbar.setNavigationOnClickListener {
            super.onBackPressed()
        }

        binding.toolbar.title = getString(R.string.app_name)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAnchorView(R.id.fab)
                .setAction("Action", null).show()
        }
    }
}