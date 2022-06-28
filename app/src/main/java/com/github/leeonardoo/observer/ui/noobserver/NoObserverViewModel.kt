package com.github.leeonardoo.observer.ui.noobserver

import androidx.lifecycle.ViewModel
import com.github.leeonardoo.observer.data.demoPosts
import com.github.leeonardoo.observer.model.Post

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
        _posts.remove(post)
        postChangeCallback?.onChange(posts)
    }

    fun addRandomPost() {
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