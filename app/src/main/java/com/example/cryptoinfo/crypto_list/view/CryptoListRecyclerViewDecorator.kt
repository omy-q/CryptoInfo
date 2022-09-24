package com.example.cryptoinfo.crypto_list.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoinfo.dp

class CryptoListRecyclerViewDecorator : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        parent.adapter?.let {
            outRect.bottom = when(parent.getChildAdapterPosition(view)) {
                state.itemCount - 1 -> 24.dp()
                else -> 0
            }
        }
    }
}