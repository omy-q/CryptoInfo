package com.example.cryptoinfo.crypto_info.model

import com.google.gson.annotations.SerializedName

data class RemoteCryptoInfoDescription(
    @SerializedName("en")
    val en: String
)

fun RemoteCryptoInfoDescription.asDomain() = this.en
