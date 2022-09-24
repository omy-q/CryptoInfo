package com.example.cryptoinfo.base

interface Binder<T> {
    fun bind(data: T)
}