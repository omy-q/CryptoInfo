package com.example.cryptoinfo.crypto_list.facade

import com.example.cryptoinfo.crypto_list.model.TypeCurrency
import com.example.cryptoinfo.crypto_list.model.asDomain
import com.example.cryptoinfo.remote.ApiResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async

class CryptoListFacadeImplementation(
    private val scope: CoroutineScope,
    private val service: CryptoListService
) : CryptoListFacade {
    override suspend fun getCryptoCurrency(
        typeCurrency: TypeCurrency,
        page: Int
    ): ApiResult<SuccessCryptoListResult> {
        val task = scope.async {
            try {
                ApiResult.create(
                    service.getListOfCryptoCurrency(
                        currencyType = typeCurrency,
                        perPage = CryptoListFacade.PER_PAGE,
                        page = page,
                        order = CryptoListFacade.ORDER
                    ).map { it.asDomain() }
                )
            } catch (e: Exception) {
                ApiResult.create(ApiResult.CryptoInfoError())
            }
        }
        return task.await()
    }
}