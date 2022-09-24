package com.example.cryptoinfo.di

class CryptoInfoComponent {
    private val scopeModule = BaseScopeModule()
    val cryptoListModule = CryptoListModule(scopeModule)
}