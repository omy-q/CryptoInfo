package com.example.cryptoinfo.crypto_list.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptoinfo.App
import com.example.cryptoinfo.R
import com.example.cryptoinfo.base.BaseFragment
import com.example.cryptoinfo.crypto_info.view.CryptoInfoFragment
import com.example.cryptoinfo.crypto_list.model.UiCryptoListData
import com.example.cryptoinfo.databinding.FragmentCryptocurrencyListBinding

class CryptoListFragment :
    BaseFragment<FragmentCryptocurrencyListBinding>(FragmentCryptocurrencyListBinding::inflate),
    CryptoListView {

    private val presenter by lazy {
        App.requireComponent().cryptoListModule.cryptoListPresenter
    }
    private lateinit var cryptoAdapter: CryptoAdapter
    private val listener = object : CryptoListViewHolderListener {
        override fun onCLick(data: UiCryptoListData) {
            presenter.onViewHolderClicked(data)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        cryptoAdapter = CryptoAdapter(listener)
        initRecyclerView()
        initChips()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    private fun initChips() {
        binding.toolbarLayout.usd.isChecked = true
        binding.toolbarLayout.usd.setOnClickListener {
            binding.toolbarLayout.usd.isChecked = true
            binding.toolbarLayout.eur.isChecked = false
            presenter.onUsdChipClicked()
        }

        binding.toolbarLayout.eur.setOnClickListener {
            binding.toolbarLayout.usd.isChecked = false
            binding.toolbarLayout.eur.isChecked = true
            presenter.onEurChipClicked()
        }
    }

    private fun initRecyclerView() {
        with(binding.recyclerView) {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = cryptoAdapter
            addItemDecoration(CryptoListRecyclerViewDecorator())
        }
    }

    override fun setData(data: List<UiCryptoListData>) {
        cryptoAdapter.setNewData(data)
    }

    override fun updateData(data: List<UiCryptoListData>) {
        cryptoAdapter.addData(data)
    }

    override fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }

    override fun navigateToInfoScreen(data: UiCryptoListData) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, CryptoInfoFragment.newInstance(data.cryptoId, data.cryptoName))
            .commit()
    }
}