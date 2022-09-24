package com.example.cryptoinfo.crypto_info.model

import com.google.gson.annotations.SerializedName

data class RemoteCryptoInfoIcon(
    @SerializedName("thumb")
    val iconThumb: String,
    @SerializedName("small")
    val iconSmall: String,
    @SerializedName("large")
    val iconLarge: String,
)
