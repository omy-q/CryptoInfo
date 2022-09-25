package com.example.cryptoinfo.crypto_info.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import coil.load
import coil.transform.CircleCropTransformation
import com.example.cryptoinfo.App
import com.example.cryptoinfo.R
import com.example.cryptoinfo.base.BaseFragment
import com.example.cryptoinfo.base.OnBackButtonListener
import com.example.cryptoinfo.crypto_info.model.UiCryptoIndoData
import com.example.cryptoinfo.databinding.FragmentCryptocurrencyInfoBinding

class CryptoInfoFragment :
    BaseFragment<FragmentCryptocurrencyInfoBinding>(FragmentCryptocurrencyInfoBinding::inflate),
    CryptoInfoView, OnBackButtonListener {

    private val presenter by lazy {
        val id: String = arguments?.getString(ARGS_KEY_ID) ?: ""
        App.requireComponent().cryptoInfoModule.cryptoInfoPresenter(id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        val title = arguments?.getString(ARGS_KEY_NAME) ?: ""
        initToolbar(title)
        initErrorLayout()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    override fun setData(data: UiCryptoIndoData) {
        binding.toolbarLayout.toolbar.title = data.cryptoName
        binding.cryptoDescription.text = data.cryptoDescription
        binding.cryptoCategory.text = data.cryptoCategory
        binding.cryptoIcon.load(data.cryptoIcon) {
            error(R.drawable.ic_crypto)
            transformations(CircleCropTransformation())
        }
        showContent()
    }

    override fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        hideContent()
    }

    override fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }

    override fun showError() {
        binding.errorLayout.root.visibility = View.VISIBLE
        binding.errorLayout.retryButton.isEnabled = true
        binding.errorLayout.retryButton.visibility = View.VISIBLE
        hideContent()
    }

    override fun hideError() {
        binding.errorLayout.root.visibility = View.GONE
    }

    override fun onBackPressed(): Boolean {
        return false
    }

    private fun initToolbar(titleName: String) {
        with(binding.toolbarLayout.toolbar) {
            title = titleName
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }

    private fun showContent() {
        binding.cryptoDescriptionTitle.visibility = View.VISIBLE
        binding.cryptoCategoryTitle.visibility = View.VISIBLE
        binding.cryptoDescription.visibility = View.VISIBLE
        binding.cryptoCategory.visibility = View.VISIBLE
        binding.cryptoIcon.visibility = View.VISIBLE
    }

    private fun hideContent() {
        binding.cryptoDescriptionTitle.visibility = View.GONE
        binding.cryptoCategoryTitle.visibility = View.GONE
        binding.cryptoDescription.visibility = View.GONE
        binding.cryptoCategory.visibility = View.GONE
        binding.cryptoIcon.visibility = View.GONE
    }

    private fun initErrorLayout() {
        binding.errorLayout.retryButton.setOnClickListener {
            binding.errorLayout.retryButton.isEnabled = false
            binding.errorLayout.retryButton.visibility = View.GONE
            presenter.onRetryClicked()
        }
    }

    companion object {
        private const val ARGS_KEY_ID = "id"
        private const val ARGS_KEY_NAME = "name"
        fun newInstance(id: String, name: String): Fragment {
            val args = Bundle()
            args.putString(ARGS_KEY_ID, id)
            args.putString(ARGS_KEY_NAME, name)
            val fragment = CryptoInfoFragment()
            fragment.arguments = args
            return fragment
        }
    }
}