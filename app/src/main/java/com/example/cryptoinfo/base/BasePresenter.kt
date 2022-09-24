package com.example.cryptoinfo.base

import androidx.lifecycle.LifecycleCoroutineScope

open class BasePresenter<T : BaseView> {
    protected var view: T? = null
    private val scope get() = view?.scope

    fun withScope(action: LifecycleCoroutineScope.() -> Unit) {
        view?.let { scope?.let(action) }
    }

    fun withView(action: (T) -> Unit) {
        view?.apply(action)
    }

    open fun attachView(view: T) {
        this.view = view
    }

    open fun detachView() {
        view = null
    }
}