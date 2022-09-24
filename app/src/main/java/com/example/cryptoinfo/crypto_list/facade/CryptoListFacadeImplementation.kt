package com.example.cryptoinfo.crypto_list.facade

import com.example.cryptoinfo.crypto_list.model.TypeCurrency
import com.example.cryptoinfo.crypto_list.model.asDomain
import com.example.cryptoinfo.remote.ApiResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import retrofit2.HttpException

class CryptoListFacadeImplementation(
    private val scope: CoroutineScope,
    private val service: CryptoListService
) : CryptoListFacade {
    override suspend fun getCryptoCurrency(typeCurrency: TypeCurrency): ApiResult<SuccessCryptoListResult> {
        val task = scope.async {
            try {
                ApiResult.create(
                    service.getListOfCryptoCurrency(typeCurrency).map { it.asDomain() }
                )
            } catch (e: HttpException) {
                TODO()
            }
        }
        return task.await()
    }
}