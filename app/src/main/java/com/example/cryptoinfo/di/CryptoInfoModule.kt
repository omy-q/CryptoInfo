package com.example.cryptoinfo.di

import com.example.cryptoinfo.crypto_info.facade.CryptoInfoFacadeImplementation
import com.example.cryptoinfo.crypto_info.facade.CryptoInfoService
import com.example.cryptoinfo.crypto_info.presentation.CryptoInfoPresenter

class CryptoInfoModule(
    private val scopeModule: BaseScopeModule,
    retrofitModule: RetrofitModule
) {

    fun cryptoInfoPresenter(id: String): CryptoInfoPresenter {
        return CryptoInfoPresenter(cryptoInfoFacade, id)
    }

    private val cryptoInfoFacade by lazy {
        CryptoInfoFacadeImplementation(scopeModule.ioScope, cryptoInfoService)
//        MockedCryptoInfoFacadeImplementation(scopeModule.ioScope)
    }

    private val cryptoInfoService by lazy {
        retrofitModule.retrofit.create(CryptoInfoService::class.java)
    }
}