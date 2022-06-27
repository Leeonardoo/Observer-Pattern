package com.github.leeonardoo.observer.pattern

class Observable<T : Any?> {

    private var value: T? = null

    //Lista de Observers inscritos
    private val observers = mutableListOf<Observer<T?>>()

    //Adiciona um novo observer e notifica com o valor atual
    fun addObserver(observer: Observer<T?>) {
        observers.add(observer)
        observer.onChanged(value)
    }

    //Remove um observer (caso existir)
    fun removeObserver(observer: Observer<T?>) = observers.remove(observer)

    //Atualiza o valor do Observable e notifica todos os Observers com o novo valor
    fun updateValue(newValue: T?) {
        value = newValue
        observers.forEach {
            it.onChanged(newValue)
        }
    }
}