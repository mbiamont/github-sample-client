package com.mbiamont.github.core

interface BasePresenter<V> {

    var view: V?

    fun onAttachView(view: V) {
        this.view = view
    }

    fun onDetachView() {
        this.view = null
    }
}