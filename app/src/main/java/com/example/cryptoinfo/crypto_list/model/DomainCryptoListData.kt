package com.example.cryptoinfo.crypto_list.model

data class DomainCryptoListData(
    val cryptoName: String,
    val cryptoShortName: String,
    val cryptoIcon: String,
    val cryptoPrice: Float,
    val cryptoPriceChangePercent: Float
)
