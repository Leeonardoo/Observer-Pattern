package com.github.leeonardoo.observer.ui.observer

import androidx.lifecycle.ViewModel
import com.github.leeonardoo.observer.data.demoPosts
import com.github.leeonardoo.observer.model.Post
import com.github.leeonardoo.observer.pattern.Observable

class ObserverViewModel : ViewModel() {

    val posts = Observable<List<Post>>()

    init {
        val randomPosts = mutableListOf<Post>()
        repeat(3) {
            randomPosts.add(
                Post(
                    id = it,
                    text = demoPosts.random()
                )
            )
        }

        posts.updateValue(randomPosts.sortedByDescending { it.id })
    }

    fun removePost(post: Post) {
        posts.updateValue(
            //Recria a lista com um post removido
            posts.value?.toMutableList()?.also {
                it.remove(post)
            }
        )
    }

    fun addRandomPost() {
        posts.updateValue(
            //Recria a lista com um post adicionado
            posts.value?.toMutableList()?.also {
                it.add(
                    index = 0,
                    element = Post(
                        id = it.size,
                        text = demoPosts.random()
                    )
                )
            }
        )
    }
}