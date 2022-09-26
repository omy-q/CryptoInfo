package com.example.cryptoinfo.crypto_list.view

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoinfo.App
import com.example.cryptoinfo.R
import com.example.cryptoinfo.base.BaseFragment
import com.example.cryptoinfo.crypto_info.view.CryptoInfoFragment
import com.example.cryptoinfo.crypto_list.model.UiCryptoListData
import com.example.cryptoinfo.databinding.FragmentCryptocurrencyListBinding
import com.google.android.material.snackbar.Snackbar

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
    private val itemCountBeforeListScrollToDown = 10
    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager: LinearLayoutManager? =
                recyclerView.layoutManager as? LinearLayoutManager
            val totalItemCount = layoutManager?.itemCount ?: 0
            val lastVisibleItemPosition = layoutManager?.findLastVisibleItemPosition() ?: 0

            if (lastVisibleItemPosition + 1 == totalItemCount) {
                presenter.onScrolledToDown()
            } else if (lastVisibleItemPosition + 1 + itemCountBeforeListScrollToDown >= totalItemCount) {
                presenter.onPreScrolledToDown()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        cryptoAdapter = CryptoAdapter(listener)
        initRecyclerView()
        initChips()
        initErrorLayout()
        initRefreshLayout()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    private fun initRefreshLayout() {
        binding.refreshLayout.setOnRefreshListener {
            presenter.onRefresh(binding.toolbarLayout.usd.isChecked)
            binding.refreshLayout.isRefreshing = false
        }
    }

    private fun initErrorLayout() {
        binding.errorLayout.retryButton.setOnClickListener {
            binding.errorLayout.retryButton.isEnabled = false
            binding.errorLayout.retryButton.visibility = View.GONE
            presenter.onRetryClicked()
        }
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
            addOnScrollListener(scrollListener)
        }
    }

    override fun setData(newData: List<UiCryptoListData>) {
        showContent()
        val diffUtilCallback = CryptoListDiffUtilCallback(cryptoAdapter.getData(), newData)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtilCallback)
        cryptoAdapter.setNewData(newData)
        diffUtilResult.dispatchUpdatesTo(cryptoAdapter)

    }

    override fun updateData(data: List<UiCryptoListData>) {
        showContent()
        val newData = cryptoAdapter.getData() + data
        val diffUtilCallback = CryptoListDiffUtilCallback(cryptoAdapter.getData(), newData)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtilCallback)
        cryptoAdapter.addData(data)
        diffUtilResult.dispatchUpdatesTo(cryptoAdapter)
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
            .replace(
                R.id.fragment_container,
                CryptoInfoFragment.newInstance(data.cryptoId, data.cryptoName)
            )
            .addToBackStack(null)
            .commit()
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

    override fun showSnackBarError() {
        val snack = Snackbar.make(
            binding.recyclerView,
            requireContext().getString(R.string.list_screen_error_refresh),
            Snackbar.LENGTH_LONG
        )
            .setTextColor(ResourcesCompat.getColor(resources, R.color.white, null))
            .setBackgroundTint(ResourcesCompat.getColor(resources, R.color.red, null))

        val textView =
            snack.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.setTextAppearance(R.style.TextCryptoInfo_Small_RobotoRegular)
        snack.show()
    }

    private fun showContent() {
        binding.refreshLayout.isEnabled  = true
        binding.recyclerView.visibility = View.VISIBLE
    }

    private fun hideContent() {
        binding.refreshLayout.isEnabled  = false
        binding.recyclerView.visibility = View.GONE
    }
}