package com.example.cryptoinfo

import android.content.res.Resources
import kotlin.math.sign

fun Float.dp(): Float {
    return this * Resources.getSystem().displayMetrics.density
}

fun Int.dp(): Int {
    return (this * Resources.getSystem().displayMetrics.density).toInt()
}

fun Float.isNegative(): Boolean {
    return this.sign == -1f
}

fun Float.isPositive(): Boolean {
    return this.sign == 1f || this.sign == 0f
}
