package com.github.leeonardoo.observer.ui.observer

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.leeonardoo.observer.R
import com.github.leeonardoo.observer.databinding.ActivityObserverBinding
import com.github.leeonardoo.observer.model.Post
import com.github.leeonardoo.observer.pattern.Observer
import com.github.leeonardoo.observer.util.applyNavbarInsets

class ObserverActivity : AppCompatActivity(R.layout.activity_observer) {

    private val binding by viewBinding(ActivityObserverBinding::bind, R.id.coordinator_layout)
    private val viewModel: ObserverViewModel by viewModels()

    private lateinit var postsObserver: Observer<List<Post>?>

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setupActionBar()

        //Cria o adapter apenas uma vez e atribui ele ao RecyclerView
        val adapter = PostAdapter(viewModel.posts.value ?: listOf()) {
            viewModel.removePost(it)
        }
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        //Adiciona o padding da barra de navegação
        binding.recyclerView.applyNavbarInsets()

        //Adiciona um novo observer para voltar ao topo quando um novo post é adicionado e o primeiro item visível é o primeiro post
        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)
                val firstPostVisible = layoutManager.findFirstVisibleItemPosition() == 0

                if (positionStart == 0 && firstPostVisible) {
                    binding.recyclerView.scrollToPosition(0)
                }
            }
        })

        postsObserver = Observer { post ->
            //Atualiza o adapter sempre que a lista de posts for alterada
            adapter.updateList(post ?: listOf())
        }

        //Adiciona o Observer criado ao Observable para começar a receber as atualizações
        viewModel.posts.addObserver(postsObserver)

        binding.fab.setOnClickListener {
            viewModel.addRandomPost()
        }
    }

    override fun onDestroy() {
        //Remove o antigo observer antes de destruir a Activity para não haver vazamento de memória
        viewModel.posts.removeObserver(postsObserver)
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