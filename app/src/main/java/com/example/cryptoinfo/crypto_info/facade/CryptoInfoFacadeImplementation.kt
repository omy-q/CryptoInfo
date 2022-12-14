package com.example.cryptoinfo.crypto_info.facade

import com.example.cryptoinfo.crypto_info.model.asDomain
import com.example.cryptoinfo.remote.ApiResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async

class CryptoInfoFacadeImplementation(
    private val scope: CoroutineScope,
    private val service: CryptoInfoService
) : CryptoInfoFacade {
    override suspend fun getCryptoInfo(cryptoId: String): ApiResult<SuccessCryptoInfoResult> {
        val task = scope.async {
            try {
                ApiResult.create(
                    service.getCryptoInfo(cryptoId).asDomain()
                )
            } catch (e: Exception) {
                ApiResult.create(ApiResult.CryptoInfoError())
            }
        }
        return task.await()
    }
}