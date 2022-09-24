package com.example.cryptoinfo.di

import com.example.cryptoinfo.remote.RetrofitBuilder

class RetrofitModule {
    val retrofit by lazy {
        RetrofitBuilder.build()
    }
}