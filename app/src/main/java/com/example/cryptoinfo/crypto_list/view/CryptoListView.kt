package com.example.cryptoinfo.crypto_list.view

import com.example.cryptoinfo.base.BaseView
import com.example.cryptoinfo.crypto_list.model.UiCryptoListData

interface CryptoListView : BaseView {
    fun setData(data: List<UiCryptoListData>)
    fun updateData(data: List<UiCryptoListData>)
    fun showLoading()
    fun hideLoading()
    fun navigateToInfoScreen(data: UiCryptoListData)
    fun showError()
    fun hideError()
}