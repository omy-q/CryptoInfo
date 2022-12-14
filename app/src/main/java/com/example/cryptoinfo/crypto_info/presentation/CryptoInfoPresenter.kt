package com.example.cryptoinfo.crypto_info.presentation

import com.example.cryptoinfo.base.BasePresenter
import com.example.cryptoinfo.crypto_info.facade.CryptoInfoFacade
import com.example.cryptoinfo.crypto_info.model.asUi
import com.example.cryptoinfo.crypto_info.view.CryptoInfoView
import kotlinx.coroutines.launch

class CryptoInfoPresenter(
    private val facade: CryptoInfoFacade,
    private val cryptoId: String
) : BasePresenter<CryptoInfoView>() {

    override fun attachView(view: CryptoInfoView) {
        super.attachView(view)
        loadData(view)
        view.showLoading()
    }

    fun onRetryClicked() {
        withView { view ->
            view.showLoading()
            loadData(view)
        }
    }

    private fun loadData(view: CryptoInfoView) {
        view.hideError()
        withScope {
            launch {
                facade.getCryptoInfo(cryptoId)
                    .withResult { result ->
                        view.setData(result.asUi())
                        view.hideLoading()
                    }
                    .withError {
                        view.hideLoading()
                        view.showError()
                    }
            }
        }
    }
}
