package com.github.leeonardoo.observer.ui.noobserver

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.leeonardoo.observer.data.demoPosts
import com.github.leeonardoo.observer.model.Post
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NoObserverViewModel : ViewModel() {

    private val _posts = mutableListOf<Post>()
    val posts: List<Post> = _posts

    var postChangeCallback: PostChangeCallback? = null

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

        _posts.addAll(randomPosts.sortedByDescending { it.id })
        postChangeCallback?.onChange(posts)
    }

    fun removePost(post: Post) {
        viewModelScope.launch {
            delay(2000) //Simulando uma ação demorada

            _posts.remove(post)
            postChangeCallback?.onChange(posts)
        }
    }

    fun addRandomPost() {
        viewModelScope.launch {
            delay(2000) //Simulando uma ação demorada

            _posts.add(
                index = 0,
                element = Post(
                    id = _posts.size,
                    text = demoPosts.random()
                )
            )
            postChangeCallback?.onChange(posts)
        }
    }
}