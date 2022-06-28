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

        //Cria o adapter inicial e atribui ele ao ListView
        val adapter = PostAdapter(
            context = this,
            posts = viewModel.posts,
            onClickRemove = { viewModel.removePost(it) }
        )
        binding.listView.adapter = adapter

        //Adiciona o padding da barra de navegação
        binding.listView.applyNavbarInsets()

        //Quando a lista é atualizada, um novo adapter é criado e atribuído, não há como notificar uma mudança sem observer
        viewModel.postChangeCallback = PostChangeCallback {
            val newAdapter = PostAdapter(
                context = this,
                posts = it ?: listOf(),
                onClickRemove = { post -> viewModel.removePost(post) }
            )

            binding.listView.adapter = newAdapter
        }

        binding.fab.setOnClickListener {
            viewModel.addRandomPost()
        }
    }

    override fun onDestroy() {
        //Remove o antigo callback antes de destruir a Activity para não haver vazamento de memória
        viewModel.postChangeCallback = null
        super.onDestroy()
    }

    private fun setupActionBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbar.setNavigationOnClickListener {
            super.onBackPressed()
        }
    }
}