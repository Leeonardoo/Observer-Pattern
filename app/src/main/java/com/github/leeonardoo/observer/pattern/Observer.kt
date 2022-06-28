package com.github.leeonardoo.observer.pattern

@FunctionalInterface
fun interface Observer<T> {

    fun onChanged(value: T)
}