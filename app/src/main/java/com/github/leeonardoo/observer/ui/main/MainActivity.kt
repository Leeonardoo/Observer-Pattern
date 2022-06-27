package com.github.leeonardoo.observer.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.github.leeonardoo.observer.R
import com.github.leeonardoo.observer.databinding.ActivityMainBinding
import com.github.leeonardoo.observer.ui.noobserver.NoObserverActivity
import com.github.leeonardoo.observer.ui.observer.ObserverActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = getString(R.string.app_name)

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