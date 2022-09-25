package com.example.cryptoinfo.crypto_list.view

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.cryptoinfo.R
import com.example.cryptoinfo.base.Binder
import com.example.cryptoinfo.crypto_list.model.UiCryptoListData
import com.example.cryptoinfo.databinding.CryptocurrencyViewHolderBinding

class CryptoListViewHolder(
    private val binding: CryptocurrencyViewHolderBinding,
    private val listener: CryptoListViewHolderListener
) :
    RecyclerView.ViewHolder(binding.root), Binder<UiCryptoListData> {
    override fun bind(data: UiCryptoListData) {
        binding.cryptoName.text = data.cryptoName
        binding.cryptoShortName.text = data.cryptoShortName
        binding.cryptoIcon.load(data.cryptoIcon) {
            error(R.drawable.ic_crypto)
            transformations(CircleCropTransformation())
        }
        binding.cryptoPrice.currencyType = data.cryptoType
        binding.cryptoPrice.price = data.cryptoPrice
        binding.cryptoPriceChangePercent.percent = data.cryptoPriceChangePercent
        binding.cryptoLayout.setOnClickListener {
            listener.onCLick(data)
        }
    }
}