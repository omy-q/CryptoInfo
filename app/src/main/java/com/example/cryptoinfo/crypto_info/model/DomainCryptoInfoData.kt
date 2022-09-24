package com.example.cryptoinfo.crypto_info.model

data class DomainCryptoInfoData(
    val cryptoId: String,
    val cryptoName: String,
    val cryptoIcon: String,
    val cryptoDescription: String,
    val cryptoCategory: List<String>
)

fun DomainCryptoInfoData.asUi(): UiCryptoIndoData {
    var categories: String = ""
    this.cryptoCategory.map {
        categories = "$categories, "
    }
    return UiCryptoIndoData(
        cryptoId = this.cryptoId,
        cryptoName = this.cryptoName,
        cryptoIcon = this.cryptoIcon,
        cryptoDescription = this.cryptoDescription,
        cryptoCategory = categories
    )
}
