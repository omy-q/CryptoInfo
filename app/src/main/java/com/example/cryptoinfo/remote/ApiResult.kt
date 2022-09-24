package com.example.cryptoinfo.remote

class ApiResult<T> private constructor(private val result: T? = null) {
    fun withResult(action: (T) -> Unit): ApiResult<T> {
        result?.let(action)
        return this
    }

    companion object {
        fun <T> create(result: T) = ApiResult(result = result)
    }
}