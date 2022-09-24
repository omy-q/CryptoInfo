package com.example.cryptoinfo.crypto_info.facade

import kotlinx.coroutines.CoroutineScope

class MockedCryptoInfoFacadeImplementation(
    private val scope: CoroutineScope
) : CryptoInfoFacade {
}