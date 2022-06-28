package com.github.leeonardoo.observer.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.leeonardoo.observer.R
import com.github.leeonardoo.observer.databinding.ActivityMainBinding
import com.github.leeonardoo.observer.ui.noobserver.NoObserverActivity
import com.github.leeonardoo.observer.ui.observer.ObserverActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind, R.id.coordinator_layout)

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbar)

        binding.observerButton.setOnClickListener {
            val intent = Intent(this, ObserverActivity::class.java)
            startActivity(intent)
        }

        binding.noObserverButton.setOnClickListener {
            val intent = Intent(this, NoObserverActivity::class.java)
            startActivity(intent)
        }
    }
}