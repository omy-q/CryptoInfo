package com.example.cryptoinfo.crypto_list.facade

import com.example.cryptoinfo.crypto_list.model.DomainCryptoListData
import com.example.cryptoinfo.crypto_list.model.TypeCurrency
import com.example.cryptoinfo.remote.ApiResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import retrofit2.HttpException

class MockedCryptoListFacadeImplementation(
    private val scope: CoroutineScope
) : CryptoListFacade {
    override suspend fun getCryptoCurrency(
        typeCurrency: TypeCurrency,
        page: Int
    ): ApiResult<SuccessCryptoListResult> {
        val task = scope.async {
            try {
                delay(3000)
                ApiResult.create(mockedData)

            } catch (e: HttpException) {
                TODO()
            }
        }
        return task.await()
    }
}

val mockedData: List<DomainCryptoListData> = listOf<DomainCryptoListData>(
    DomainCryptoListData(
        "bitcoin",
        "bitcoin",
        "btc",
        "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
        19107.17f,
        0.96422f
    ),
    DomainCryptoListData(
        "bitcoin",
        "bitcoin",
        "btc",
        "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
        19107.17f,
        0.96422f
    ),
    DomainCryptoListData(
        "bitcoin",
        "bitcoin",
        "btc",
        "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
        19107.17f,
        0.96422f
    ),
    DomainCryptoListData(
        "bitcoin",
        "bitcoin",
        "btc",
        "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
        19107.17f,
        0.96422f
    ),
    DomainCryptoListData(
        "bitcoin",
        "bitcoin",
        "btc",
        "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
        19107.17f,
        0.96422f
    ),
    DomainCryptoListData(
        "bitcoin",
        "bitcoin",
        "btc",
        "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
        19107.17f,
        0.96422f
    ),
    DomainCryptoListData(
        "bitcoin",
        "bitcoin",
        "btc",
        "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
        19107.17f,
        0.96422f
    ),
    DomainCryptoListData(
        "bitcoin",
        "bitcoin",
        "btc",
        "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
        19107.17f,
        0.96422f
    ),
    DomainCryptoListData(
        "bitcoin",
        "bitcoin",
        "btc",
        "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
        19107.17f,
        0.96422f
    ),
    DomainCryptoListData(
        "bitcoin",
        "bitcoin",
        "btc",
        "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
        19107.17f,
        0.96422f
    ),
    DomainCryptoListData(
        "bitcoin",
        "bitcoin",
        "btc",
        "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
        19107.17f,
        0.96422f
    ),
    DomainCryptoListData(
        "bitcoin",
        "bitcoin",
        "btc",
        "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
        19107.17f,
        0.96422f
    ),
    DomainCryptoListData(
        "bitcoin",
        "bitcoin",
        "btc",
        "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
        19107.17f,
        0.96422f
    ),
    DomainCryptoListData(
        "bitcoin",
        "bitcoin",
        "btc",
        "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
        19107.17f,
        0.96422f
    ),
    DomainCryptoListData(
        "bitcoin",
        "bitcoin",
        "btc",
        "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
        19107.17f,
        0.96422f
    ),
    DomainCryptoListData(
        "bitcoin",
        "bitcoin",
        "btc",
        "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
        19107.17f,
        0.96422f
    ),
    DomainCryptoListData(
        "bitcoin",
        "bitcoin",
        "btc",
        "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
        19107.17f,
        0.96422f
    ),
    DomainCryptoListData(
        "bitcoin",
        "bitcoin",
        "btc",
        "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
        19107.17f,
        0.96422f
    ),
    DomainCryptoListData(
        "bitcoin",
        "bitcoin",
        "btc",
        "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
        19107.17f,
        0.96422f
    ),
    DomainCryptoListData(
        "bitcoin",
        "bitcoin",
        "btc",
        "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
        19107.17f,
        0.96422f
    ),
    DomainCryptoListData(
        "bitcoin",
        "bitcoin",
        "btc",
        "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
        19107.17f,
        0.96422f
    ),
    DomainCryptoListData(
        "bitcoin",
        "bitcoin",
        "btc",
        "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
        19107.17f,
        0.96422f
    ),
    DomainCryptoListData(
        "bitcoin",
        "bitcoin",
        "btc",
        "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
        19107.17f,
        0.96422f
    ),
    DomainCryptoListData(
        "bitcoin",
        "bitcoin",
        "btc",
        "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
        19107.17f,
        0.96422f
    ),
)