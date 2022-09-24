package com.example.cryptoinfo.crypto_list.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptoinfo.base.BaseFragment
import com.example.cryptoinfo.databinding.FragmentCryptocurrencyListBinding

class CryptoListFragment :
    BaseFragment<FragmentCryptocurrencyListBinding>(FragmentCryptocurrencyListBinding::inflate) {

    private lateinit var cryptoAdapter: CryptoAdapter

    private val listener = object : CryptoViewHolderListener {
        override fun onCLick() {
            TODO("Not yet implemented")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cryptoAdapter = CryptoAdapter(listener)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        with(binding.recyclerView) {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = cryptoAdapter
        }
    }
}