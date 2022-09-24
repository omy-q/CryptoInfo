package com.example.cryptoinfo.crypto_info.view

import android.os.Bundle
import android.view.View
import coil.load
import coil.transform.CircleCropTransformation
import com.example.cryptoinfo.App
import com.example.cryptoinfo.R
import com.example.cryptoinfo.base.BaseFragment
import com.example.cryptoinfo.crypto_info.model.UiCryptoIndoData
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

    override fun setData(data: UiCryptoIndoData) {
        binding.cryptoCategoryTitle.visibility = View.VISIBLE
        binding.cryptoCategoryTitle.visibility = View.VISIBLE
        binding.cryptoDescription.text = data.cryptoDescription
        binding.cryptoCategory.text = data.cryptoCategory
        binding.cryptoIcon.load(data.cryptoIcon) {
            error(R.drawable.ic_crypto)
            transformations(CircleCropTransformation())
        }
    }

    override fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.cryptoCategoryTitle.visibility = View.GONE
        binding.cryptoCategoryTitle.visibility = View.GONE
    }

    override fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }
}