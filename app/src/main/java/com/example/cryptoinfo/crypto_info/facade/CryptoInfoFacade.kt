package com.example.cryptoinfo.crypto_info.facade

import com.example.cryptoinfo.crypto_info.model.DomainCryptoInfoData
import com.example.cryptoinfo.remote.ApiResult

typealias SuccessCryptoInfoResult = DomainCryptoInfoData

interface CryptoInfoFacade {
    suspend fun getCryptoInfo(cryptoId: String): ApiResult<SuccessCryptoInfoResult>
}