package com.example.cryptoinfo.crypto_info.model

import com.google.gson.annotations.SerializedName

data class RemoteCryptoInfoData(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val icon: RemoteCryptoInfoIcon,
    @SerializedName("description")
    val description: RemoteCryptoInfoDescription,
    @SerializedName("categories")
    val categories: List<String>,

    )

fun RemoteCryptoInfoData.asDomain(): DomainCryptoInfoData {
    return DomainCryptoInfoData(
        cryptoId = this.id,
        cryptoName = this.name,
        cryptoIcon = this.icon.iconLarge,
        cryptoDescription = this.description.asDomain(),
        cryptoCategory = this.categories
    )
}