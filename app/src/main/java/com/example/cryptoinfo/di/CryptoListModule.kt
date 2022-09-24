package com.example.cryptoinfo.di

import com.example.cryptoinfo.crypto_list.facade.CryptoListFacade
import com.example.cryptoinfo.crypto_list.facade.MockedCryptoListFacadeImplementation
import com.example.cryptoinfo.crypto_list.presentation.CryptoListPresenter

class CryptoListModule(
    private val scopeModule: BaseScopeModule
) {
    val cryptoListPresenter by lazy {
        CryptoListPresenter(cryptoListFacade)
    }

    private val cryptoListFacade: CryptoListFacade by lazy {
        MockedCryptoListFacadeImplementation(scopeModule.ioScope)
    }
}