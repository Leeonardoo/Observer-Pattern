package com.github.leeonardoo.observer.pattern

@FunctionalInterface
interface Observer<T> {

    //Chamado quando um valor de um Observable é atualizado
    fun onChanged(value: T)
}