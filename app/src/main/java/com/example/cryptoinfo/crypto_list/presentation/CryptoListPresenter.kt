package com.example.cryptoinfo.crypto_list.presentation

import com.example.cryptoinfo.base.BasePresenter
import com.example.cryptoinfo.crypto_list.facade.CryptoListFacade
import com.example.cryptoinfo.crypto_list.model.DomainCryptoListData
import com.example.cryptoinfo.crypto_list.model.TypeCurrency
import com.example.cryptoinfo.crypto_list.model.UiCryptoListData
import com.example.cryptoinfo.crypto_list.model.asUi
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
        if (lastCurrencyType != TypeCurrency.USD) {
            withView { view ->
                view.showLoading()
                loadData(view, TypeCurrency.USD)
            }
        }
    }

    fun onEurChipClicked() {
        if (lastCurrencyType != TypeCurrency.EUR) {
            withView { view ->
                view.showLoading()
                loadData(view, TypeCurrency.EUR)
            }
        }
    }

    fun onViewHolderClicked() {
        withView { view ->
            view.navigateToInfoScreen()
        }
    }

    private fun loadData(view: CryptoView, currencyType: TypeCurrency) {
        withScope {
            launch {
                facade.getCryptoCurrency(currencyType).withResult { result ->
                    if (lastCurrencyType == currencyType) {
                        view.updateData(result.toUiData(currencyType))
                    } else {
                        view.setData(result.toUiData(currencyType))
                    }
                    lastCurrencyType = currencyType
                    view.hideLoading()
                }
            }
        }
    }

    private fun List<DomainCryptoListData>.toUiData(currencyType: TypeCurrency): List<UiCryptoListData> {
        return this.map { cryptoData ->
            cryptoData.asUi(currencyType)
        }
    }
}