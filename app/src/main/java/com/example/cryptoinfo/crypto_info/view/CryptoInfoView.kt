package com.example.cryptoinfo.crypto_info.view

import com.example.cryptoinfo.base.BaseView
import com.example.cryptoinfo.crypto_info.model.UiCryptoIndoData

interface CryptoInfoView : BaseView {
    fun setData(data: UiCryptoIndoData)
    fun showLoading()
    fun hideLoading()
    fun showError()
    fun hideError()
}