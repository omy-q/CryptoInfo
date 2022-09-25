package com.example.cryptoinfo.crypto_list.presentation

import com.example.cryptoinfo.base.BasePresenter
import com.example.cryptoinfo.crypto_list.facade.CryptoListFacade
import com.example.cryptoinfo.crypto_list.model.DomainCryptoListData
import com.example.cryptoinfo.crypto_list.model.TypeCurrency
import com.example.cryptoinfo.crypto_list.model.UiCryptoListData
import com.example.cryptoinfo.crypto_list.model.asUi
import com.example.cryptoinfo.crypto_list.view.CryptoListView
import kotlinx.coroutines.launch

class CryptoListPresenter(
    private val facade: CryptoListFacade
) : BasePresenter<CryptoListView>() {

    private var lastCurrencyType: TypeCurrency = TypeCurrency.USD
    private var currentCurrencyType: TypeCurrency = TypeCurrency.USD

    override fun attachView(view: CryptoListView) {
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

    fun onViewHolderClicked(data: UiCryptoListData) {
        withView { view ->
            view.navigateToInfoScreen(data)
        }
    }

    fun onRetryClicked() {
        withView { view ->
            view.showLoading()
            loadData(view, currentCurrencyType)
        }
    }

    private fun loadData(view: CryptoListView, currencyType: TypeCurrency) {
        currentCurrencyType = currencyType
        view.hideError()
        withScope {
            launch {
                facade.getCryptoCurrency(currencyType)
                    .withResult { result ->
                        if (lastCurrencyType == currencyType) {
                            view.updateData(result.toUiData(currencyType))
                        } else {
                            view.setData(result.toUiData(currencyType))
                        }
                        lastCurrencyType = currencyType
                        view.hideLoading()
                    }
                    .withError {
                        view.hideLoading()
                        view.showError()
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