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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    override fun setData(data: UiCryptoIndoData) {
        binding.cryptoCategoryTitle.visibility = View.VISIBLE
        binding.cryptoCategoryTitle.visibility = View.VISIBLE
        binding.toolbarLayout.toolbar.title = data.cryptoName
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