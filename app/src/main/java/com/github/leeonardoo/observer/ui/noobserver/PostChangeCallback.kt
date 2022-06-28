package com.github.leeonardoo.observer.ui.noobserver

import com.github.leeonardoo.observer.model.Post

@FunctionalInterface
fun interface PostChangeCallback {

    fun onChange(posts: List<Post>?)

}