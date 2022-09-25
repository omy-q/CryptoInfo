package com.example.cryptoinfo.crypto_list.facade

import com.example.cryptoinfo.crypto_list.model.RemoteCryptoListData
import com.example.cryptoinfo.crypto_list.model.TypeCurrency
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoListService {
    @GET("coins/markets")
    suspend fun getListOfCryptoCurrency(
        @Query("vs_currency")
        currencyType: TypeCurrency,
        @Query("per_page")
        perPage: Int,
        @Query("page")
        page: Int,
        @Query("order")
        order: String
    ): List<RemoteCryptoListData>
}