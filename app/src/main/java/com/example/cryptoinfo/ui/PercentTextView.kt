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
import java.text.DecimalFormat
import kotlin.math.abs

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
        when {
            value.isNegative() -> {
                percentTextView.setTextColor(colorNegative)
            }
            value.isPositive() -> {
                percentTextView.setTextColor(colorPossitive)
            }
            else -> {
                percentTextView.setTextColor(colorPossitive)
            }
        }

        percentTextView.text = context.getString(
            R.string.percent_text_view,
            summaryToString(value)
        )
    }

    private fun summaryToString(summary: Float): String {
        val format = DecimalFormat("###,##0.00")
        format.positivePrefix = context.getString(R.string.plus_prefix)
        format.negativePrefix = context.getString(R.string.minus_prefix)
        return format.format(summary)
    }

    companion object {
        const val percentDefaultValue: Float = 0.00F
    }
}