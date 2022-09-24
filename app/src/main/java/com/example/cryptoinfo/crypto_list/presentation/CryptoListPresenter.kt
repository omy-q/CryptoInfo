package com.example.cryptoinfo.crypto_list.presentation

import com.example.cryptoinfo.base.BasePresenter
import com.example.cryptoinfo.crypto_list.facade.CryptoListFacade
import com.example.cryptoinfo.crypto_list.model.TypeCurrency
import com.example.cryptoinfo.crypto_list.view.CryptoView
import kotlinx.coroutines.launch

class CryptoListPresenter(
    private val facade: CryptoListFacade
) : BasePresenter<CryptoView>() {

    private var lastCurrencyType: TypeCurrency = TypeCurrency.USD

    override fun attachView(view: CryptoView) {
        super.attachView(view)
        view.showLoading()
        loadData(view, TypeCurrency.USD)
    }

    fun onUsdChipClicked() {
        withView { view ->
            view.showLoading()
            loadData(view, TypeCurrency.USD)
        }
    }

    fun onEurChipClicked() {
        withView { view ->
            view.showLoading()
            loadData(view, TypeCurrency.EUR)
        }
    }

    private fun loadData(view: CryptoView, cryptoTypeCurrency: TypeCurrency) {
        withScope {
            launch {
                facade.getCryptoCurrency(cryptoTypeCurrency).withResult { result ->
                    if (lastCurrencyType == cryptoTypeCurrency) {
                        view.updateData(result)
                    } else {
                        view.setData(result)
                    }
                    lastCurrencyType = cryptoTypeCurrency
                    view.hideLoading()
                }
            }
        }
    }
}