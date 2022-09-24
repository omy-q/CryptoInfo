package com.example.cryptoinfo.crypto_list.model

data class DomainCryptoListData(
    val cryptoName: String,
    val cryptoShortName: String,
    val cryptoIcon: String,
    val cryptoPrice: Float,
    val cryptoPriceChangePercent: Float
)

fun DomainCryptoListData.asUi(currencyType: TypeCurrency): UiCryptoListData {
    return UiCryptoListData(
        cryptoType = currencyType,
        cryptoName = this.cryptoName,
        cryptoShortName = this.cryptoShortName,
        cryptoIcon = this.cryptoIcon,
        cryptoPrice = this.cryptoPrice,
        cryptoPriceChangePercent = this.cryptoPriceChangePercent
    )
}
