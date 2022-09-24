package com.example.cryptoinfo.crypto_list.view

import com.example.cryptoinfo.base.BaseView
import com.example.cryptoinfo.crypto_list.model.UiCryptoListData

interface CryptoView : BaseView {
    fun setData(data: List<UiCryptoListData>)
    fun updateData(data: List<UiCryptoListData>)
    fun showLoading()
    fun hideLoading()
    fun navigateToInfoScreen()
}