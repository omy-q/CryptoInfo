package com.example.cryptoinfo.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatTextView
import com.example.cryptoinfo.R
import com.example.cryptoinfo.crypto_list.model.TypeCurrency
import java.text.DecimalFormat

class PriceTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    var price: Float = priceDefaultValue
        set(value) {
            updatePrice(value)
            field = value
        }

    var currencyType: TypeCurrency = currencyTypeDefaultValue

    private val priceTextView: AppCompatTextView

    init {
        inflate(context, R.layout.price_text_view, this)
        priceTextView = findViewById(R.id.price_text_view)
    }

    private fun updatePrice(value: Float) {
        val sign: String = when (currencyType) {
            TypeCurrency.USD -> {
                context.getString(R.string.usd_prefix)
            }
            TypeCurrency.EUR -> {
                context.getString(R.string.eur_prefix)
            }
        }
        priceTextView.text = context.getString(
            R.string.price_text_view,
            sign,
            summaryToString(value)
        )
    }

    private fun summaryToString(summary: Float): String {
        val format = DecimalFormat("###,##0.00")
        return format.format(summary)
    }

    companion object {
        const val priceDefaultValue = 0.0f
        val currencyTypeDefaultValue = TypeCurrency.USD
    }
}