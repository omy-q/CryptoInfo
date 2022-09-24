package com.example.cryptoinfo.crypto_list.view

import com.example.cryptoinfo.base.BaseView
import com.example.cryptoinfo.crypto_list.model.DomainCryptoListData

interface CryptoView : BaseView {
    fun setData(data: List<DomainCryptoListData>)
    fun updateData(data: List<DomainCryptoListData>)
    fun showLoading()
    fun hideLoading()
}