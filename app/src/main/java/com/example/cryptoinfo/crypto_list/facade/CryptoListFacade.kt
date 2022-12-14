package com.example.cryptoinfo.crypto_list.facade

import com.example.cryptoinfo.crypto_list.model.DomainCryptoListData
import com.example.cryptoinfo.crypto_list.model.TypeCurrency
import com.example.cryptoinfo.remote.ApiResult

typealias SuccessCryptoListResult = List<DomainCryptoListData>

interface CryptoListFacade {
    suspend fun getCryptoCurrency(typeCurrency: TypeCurrency, page: Int): ApiResult<SuccessCryptoListResult>

    companion object {
        const val PER_PAGE = 30
        const val ORDER = "market_cap_desc"
    }
}