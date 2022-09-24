package com.example.cryptoinfo.di

import com.example.cryptoinfo.crypto_info.facade.MockedCryptoInfoFacadeImplementation
import com.example.cryptoinfo.crypto_info.presentation.CryptoInfoPresenter

class CryptoInfoModule(
    private val scopeModule: BaseScopeModule
) {
    val cryptoInfoPresenter by lazy {
        CryptoInfoPresenter(cryptoInfoFacade)
    }

    private val cryptoInfoFacade by lazy {
        MockedCryptoInfoFacadeImplementation(scopeModule.ioScope)
    }
}