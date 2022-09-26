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

    private var lastCurrencyType: TypeCurrency = TypeCurrency.EUR
    private var currentCurrencyType: TypeCurrency = TypeCurrency.USD

    private var page: Int = 1
    private var isPageEnd: Boolean = false
    private var isDataLoading: Boolean = false
    private var isRefreshError: Boolean = false

    override fun attachView(view: CryptoListView) {
        super.attachView(view)
        view.showLoading()
        loadData(view, TypeCurrency.USD)
    }

    override fun detachView() {
        super.detachView()
        initData()
    }

    fun onRefresh(isUsdChipChecked: Boolean) {
        if (!isDataLoading) {
            initData()
            if (isUsdChipChecked) {
                lastCurrencyType = TypeCurrency.EUR
                currentCurrencyType = TypeCurrency.USD
            } else {
                lastCurrencyType = TypeCurrency.USD
                currentCurrencyType = TypeCurrency.EUR
            }
            isRefreshError = true
            withView { view ->
                view.showLoading()
                loadData(view, currentCurrencyType)
            }
        }
    }

    fun onScrolledToDown() {
        if (isDataLoading) {
            withView { view ->
                view.showLoading()
            }
        }
    }

    fun onPreScrolledToDown() {
        if (!isDataLoading) {
            withView { view ->
                loadData(view, currentCurrencyType)
            }
        }
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
        if (currencyType != currentCurrencyType) {
            page = 1
            currentCurrencyType = currencyType
        }
        view.hideError()
        if (!isPageEnd) {
            isDataLoading = true
            withScope {
                launch {
                    facade.getCryptoCurrency(currencyType, page)
                        .withResult { result ->
                            isDataLoading = false
                            page += result.size
                            if (result.size < CryptoListFacade.PER_PAGE) {
                                isPageEnd = true
                            }
                            if (lastCurrencyType == currencyType) {
                                view.updateData(result.toUiData(currencyType))
                            } else {
                                view.setData(result.toUiData(currencyType))
                            }
                            lastCurrencyType = currencyType
                            view.hideLoading()
                        }
                        .withError {
                            isDataLoading = false
                            view.hideLoading()
                            if (isRefreshError) {
                                isRefreshError = false
                                view.showSnackBarError()
                            } else {
                                view.showError()
                            }
                        }
                }
            }
        } else {
            isDataLoading = false
        }
    }

    private fun initData() {
        lastCurrencyType = TypeCurrency.EUR
        currentCurrencyType = TypeCurrency.USD
        page = 1
        isPageEnd = false
        isDataLoading = false
        isRefreshError = false
    }

    private fun List<DomainCryptoListData>.toUiData(currencyType: TypeCurrency): List<UiCryptoListData> {
        return this.map { cryptoData ->
            cryptoData.asUi(currencyType)
        }
    }
}