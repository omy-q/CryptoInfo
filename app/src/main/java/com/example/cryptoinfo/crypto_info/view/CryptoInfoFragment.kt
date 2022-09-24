package com.example.cryptoinfo.crypto_info.view

import android.os.Bundle
import android.view.View
import com.example.cryptoinfo.App
import com.example.cryptoinfo.base.BaseFragment
import com.example.cryptoinfo.databinding.FragmentCryptocurrencyInfoBinding

class CryptoInfoFragment :
    BaseFragment<FragmentCryptocurrencyInfoBinding>(FragmentCryptocurrencyInfoBinding::inflate),
    CryptoInfoView {

    private val presenter by lazy {
        App.requireComponent().cryptoInfoModule.cryptoInfoPresenter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }
}