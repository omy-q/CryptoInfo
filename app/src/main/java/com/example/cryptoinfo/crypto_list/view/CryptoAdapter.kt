package com.example.cryptoinfo.crypto_list.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoinfo.base.Binder
import com.example.cryptoinfo.crypto_list.model.UiCryptoListData
import com.example.cryptoinfo.databinding.CryptocurrencyViewHolderBinding

class CryptoAdapter(
    private val listener: CryptoListViewHolderListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val data: MutableList<UiCryptoListData> = mutableListOf()

    fun addData(data: List<UiCryptoListData>) {
        this.data.addAll(data)
    }

    fun setNewData(data: List<UiCryptoListData>) {
        this.data.clear()
        this.data.addAll(data)
    }

    fun getData() = data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CryptoListViewHolder(
            CryptocurrencyViewHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), listener
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? Binder<UiCryptoListData>)?.bind(data[position])
    }

    override fun getItemCount() = data.size
}