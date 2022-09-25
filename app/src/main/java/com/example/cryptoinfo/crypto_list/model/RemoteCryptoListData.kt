package com.example.cryptoinfo.crypto_list.model

import com.google.gson.annotations.SerializedName

data class RemoteCryptoListData(
    @SerializedName("id")
    val cryptoId: String,
    @SerializedName("name")
    val cryptoName: String,
    @SerializedName("symbol")
    val cryptoShortName: String,
    @SerializedName("image")
    val cryptoIcon: String,
    @SerializedName("current_price")
    val cryptoPrice: Float,
    @SerializedName("price_change_percentage_24h")
    val cryptoChangePercent: Float
)

fun RemoteCryptoListData.asDomain(): DomainCryptoListData {
    return DomainCryptoListData(
        cryptoId = this.cryptoId,
        cryptoName = this.cryptoName,
        cryptoShortName = this.cryptoShortName,
        cryptoIcon = this.cryptoIcon,
        cryptoPrice = this.cryptoPrice,
        cryptoPriceChangePercent = this.cryptoChangePercent
    )
}
