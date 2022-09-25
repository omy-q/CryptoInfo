package com.example.cryptoinfo.crypto_list.model

data class UiCryptoListData(
    val cryptoId: String,
    val cryptoType: TypeCurrency,
    val cryptoName: String,
    val cryptoShortName: String,
    val cryptoIcon: String,
    val cryptoPrice: Float,
    val cryptoPriceChangePercent: Float
)