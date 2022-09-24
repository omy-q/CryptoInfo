package com.example.cryptoinfo.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import com.example.cryptoinfo.R
import com.example.cryptoinfo.isNegative
import com.example.cryptoinfo.isPositive

class PercentTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    var percent: Float = percentDefaultValue
        set(value) {
            updateColor(value)
            field = value
        }

    @ColorInt
    private val colorPossitive: Int

    @ColorInt
    private val colorNegative: Int

    private val percentTextView: AppCompatTextView

    init {
        colorPossitive = ResourcesCompat.getColor(resources, R.color.green, null)
        colorNegative = ResourcesCompat.getColor(resources, R.color.red, null)
        inflate(context, R.layout.percent_text_view, this)

        percentTextView = findViewById(R.id.percent_text_view)
    }

    private fun updateColor(value: Float) {
        val sign: String
        when {
            value.isNegative() -> {
                sign = context.getString(R.string.minus_prefix)
                percentTextView.setTextColor(colorNegative)
            }
            value.isPositive() -> {
                sign = context.getString(R.string.plus_prefix)
                percentTextView.setTextColor(colorPossitive)
            }
            else -> {
                sign = ""
                percentTextView.setTextColor(colorPossitive)
            }
        }

        percentTextView.text = context.getString(
            R.string.percent_text_view,
            sign,
            value
        )
    }

    companion object {
        const val percentDefaultValue: Float = 0.00F
    }
}