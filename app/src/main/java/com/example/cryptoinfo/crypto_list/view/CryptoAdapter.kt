package com.example.cryptoinfo.crypto_list.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoinfo.base.Binder
import com.example.cryptoinfo.crypto_list.model.DomainCryptoListData
import com.example.cryptoinfo.databinding.CryptocurrencyViewHolderBinding

class CryptoAdapter(
    private val listener: CryptoViewHolderListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val data: MutableList<DomainCryptoListData> = mutableListOf()

    fun addData(data: List<DomainCryptoListData>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun setNewData(data: List<DomainCryptoListData>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CryptoViewHolder(
            CryptocurrencyViewHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), listener
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? Binder<DomainCryptoListData>)?.bind(data[position])
    }

    override fun getItemCount() = data.size
}