package com.example.cryptoinfo.di

class CryptoInfoComponent {
    private val scopeModule = BaseScopeModule()
    private val retrofitModule = RetrofitModule()
    val cryptoListModule = CryptoListModule(scopeModule, retrofitModule)
    val cryptoInfoModule = CryptoInfoModule(scopeModule, retrofitModule)
}