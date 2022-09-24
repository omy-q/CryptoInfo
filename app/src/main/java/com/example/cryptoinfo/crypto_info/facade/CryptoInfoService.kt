package com.example.cryptoinfo.crypto_info.facade

import com.example.cryptoinfo.crypto_info.model.RemoteCryptoInfoData
import retrofit2.http.GET
import retrofit2.http.Path

interface CryptoInfoService {
    @GET("coins/{id}")
    suspend fun getCryptoInfo(
        @Path("id")
        id: String
    ): RemoteCryptoInfoData
}