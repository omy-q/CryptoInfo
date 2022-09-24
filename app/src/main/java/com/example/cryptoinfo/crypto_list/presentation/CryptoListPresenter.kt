package com.example.cryptoinfo.crypto_list.presentation

import com.example.cryptoinfo.base.BasePresenter
import com.example.cryptoinfo.crypto_list.facade.CryptoListFacade
import com.example.cryptoinfo.crypto_list.model.TypeCurrency
import com.example.cryptoinfo.crypto_list.view.CryptoView
import kotlinx.coroutines.launch

class CryptoListPresenter(
    private val facade: CryptoListFacade
) : BasePresenter<CryptoView>() {


}