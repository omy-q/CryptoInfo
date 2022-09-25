package com.example.cryptoinfo.crypto_info.model

import com.google.gson.annotations.SerializedName

data class RemoteCryptoInfoCategory(
    val category: String
)

fun RemoteCryptoInfoCategory.asDomain(): String = this.category
