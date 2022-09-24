package com.example.cryptoinfo.di

import com.example.cryptoinfo.crypto_info.facade.MockedCryptoInfoFacadeImplementation
import com.example.cryptoinfo.crypto_info.presentation.CryptoInfoPresenter

class CryptoInfoModule(
    private val scopeModule: BaseScopeModule
) {

    fun cryptoInfoPresenter(id: String): CryptoInfoPresenter {
        return CryptoInfoPresenter(cryptoInfoFacade, id)
    }

    private val cryptoInfoFacade by lazy {
        MockedCryptoInfoFacadeImplementation(scopeModule.ioScope)
    }
}