package com.example.cryptoinfo.crypto_list.view

import androidx.recyclerview.widget.DiffUtil
import com.example.cryptoinfo.crypto_list.model.UiCryptoListData

class CryptoListDiffUtilCallback(
    private val oldList: List<UiCryptoListData>,
    private val newList: List<UiCryptoListData>,
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].cryptoId == newList[newItemPosition].cryptoId

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}