package com.example.cryptoinfo.crypto_info.facade

import com.example.cryptoinfo.crypto_info.model.DomainCryptoInfoData
import com.example.cryptoinfo.remote.ApiResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import retrofit2.HttpException

class MockedCryptoInfoFacadeImplementation(
    private val scope: CoroutineScope
) : CryptoInfoFacade {
    override suspend fun getCryptoInfo(cryptoId: String): ApiResult<SuccessCryptoInfoResult> {
        val task = scope.async {
            try {
                delay(3000)
                ApiResult.create(mockedInfoData)

            } catch (e: HttpException) {
                TODO()
            }
        }
        return task.await()
    }
}

val mockedInfoData: DomainCryptoInfoData = DomainCryptoInfoData(
    cryptoId = "bitcoin",
    cryptoName = "Bitcoin",
    cryptoIcon = "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
    cryptoDescription = "Bitcoin is a decentralized cryptocurrency originally described in a 2008 whitepaper by a person, or group of people, using the alias Satoshi Nakamoto. It was launched soon after, in January 2009.\n" +
            "\n" +
            "Bitcoin is a peer-to-peer online currency, meaning that all transactions happen directly between equal, independent network participants, without the need for any intermediary to permit or facilitate them. Bitcoin was created, according to Nakamoto’s own words, to allow “online payments to be sent directly from one party to another without going through a financial institution.”\n" +
            "Bitcoin is a peer-to-peer online currency, meaning that all transactions happen directly between equal, independent network participants, without the need for any intermediary to permit or facilitate them. Bitcoin was created, according to Nakamoto’s own words, to allow “online payments to be sent directly from one party to another without going through a financial institution.”",
    cryptoCategory = listOf(
        "Smart Contract Platform", "Ethereum Ecosystems"
    )
)