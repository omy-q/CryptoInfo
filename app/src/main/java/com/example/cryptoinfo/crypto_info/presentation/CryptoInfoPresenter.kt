package com.example.cryptoinfo.crypto_info.presentation

import com.example.cryptoinfo.base.BasePresenter
import com.example.cryptoinfo.crypto_info.facade.CryptoInfoFacade
import com.example.cryptoinfo.crypto_info.view.CryptoInfoView

class CryptoInfoPresenter(
    private val facade: CryptoInfoFacade
): BasePresenter<CryptoInfoView>() {

}