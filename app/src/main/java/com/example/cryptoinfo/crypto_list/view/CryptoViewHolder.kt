package com.example.cryptoinfo.crypto_list.view

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.cryptoinfo.R
import com.example.cryptoinfo.base.Binder
import com.example.cryptoinfo.crypto_list.model.DomainCryptoListData
import com.example.cryptoinfo.crypto_list.model.TypeCurrency
import com.example.cryptoinfo.databinding.CryptocurrencyViewHolderBinding

class CryptoViewHolder(
    private val binding: CryptocurrencyViewHolderBinding,
    private val listener: CryptoViewHolderListener
) :
    RecyclerView.ViewHolder(binding.root), Binder<DomainCryptoListData> {
    override fun bind(data: DomainCryptoListData) {
        binding.cryptoName.text = data.cryptoName
        binding.cryptoShortName.text = data.cryptoShortName
        binding.cryptoIcon.load(data.cryptoIcon) {
            error(R.drawable.ic_crypto)
            transformations(CircleCropTransformation())
        }
        binding.cryptoPrice.currencyType = TypeCurrency.EUR
        binding.cryptoPrice.price = data.cryptoPrice
        binding.cryptoPriceChangePercent.percent = data.cryptoPriceChangePercent
    }
}