package com.example.cryptoinfo.di

import com.example.cryptoinfo.crypto_list.facade.CryptoListFacade
import com.example.cryptoinfo.crypto_list.facade.CryptoListFacadeImplementation
import com.example.cryptoinfo.crypto_list.facade.CryptoListService
import com.example.cryptoinfo.crypto_list.facade.MockedCryptoListFacadeImplementation
import com.example.cryptoinfo.crypto_list.presentation.CryptoListPresenter

class CryptoListModule(
    private val scopeModule: BaseScopeModule,
    retrofitModule: RetrofitModule
) {
    val cryptoListPresenter by lazy {
        CryptoListPresenter(cryptoListFacade)
    }

    private val cryptoListFacade: CryptoListFacade by lazy {
        CryptoListFacadeImplementation(scopeModule.ioScope, cryptoListService)
//        MockedCryptoListFacadeImplementation(scopeModule.ioScope)
    }

    private val cryptoListService by lazy {
        retrofitModule.retrofit.create(CryptoListService::class.java)
    }
}