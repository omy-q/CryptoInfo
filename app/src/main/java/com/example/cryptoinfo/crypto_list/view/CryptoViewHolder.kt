package com.example.cryptoinfo.crypto_list.view

import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoinfo.databinding.CryptocurrencyViewHolderBinding

class CryptoViewHolder(
    private val binding: CryptocurrencyViewHolderBinding,
    private val listener: CryptoViewHolderListener
) :
    RecyclerView.ViewHolder(binding.root) {
}