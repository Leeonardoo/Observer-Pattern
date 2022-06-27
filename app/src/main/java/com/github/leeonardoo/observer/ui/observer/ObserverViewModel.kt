package com.github.leeonardoo.observer.ui.observer

import androidx.lifecycle.ViewModel
import com.github.leeonardoo.observer.data.randomPostText
import com.github.leeonardoo.observer.model.Post
import com.github.leeonardoo.observer.pattern.Observable

class ObserverViewModel : ViewModel() {

    val posts = Observable<List<Post>>()

    init {
        val postsList = mutableListOf<Post>()
        repeat(3) {
            postsList.add(
                Post(
                    id = it,
                    text = randomPostText()
                )
            )
        }
    }
}