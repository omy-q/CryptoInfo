package com.example.cryptoinfo.remote

class ApiResult<T> private constructor(
    private val result: T? = null,
    private val error: CryptoInfoError? = null
) {
    fun withResult(action: (T) -> Unit): ApiResult<T> {
        result?.let(action)
        return this
    }

    fun withError(action: (CryptoInfoError) -> Unit): ApiResult<T> {
        error?.let(action)
        return this
    }

    companion object {
        fun <T> create(result: T) = ApiResult(result = result)
        fun <T> create(error: CryptoInfoError) = ApiResult<T>(error = error)
    }

    class CryptoInfoError
}