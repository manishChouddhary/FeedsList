package com.synerzip.feeds.extentions

import android.content.Context
import android.content.res.Configuration
import android.net.ConnectivityManager

fun Context.isOrientationPortrait(): Boolean =  (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)

fun Context.getDimen(dimen: Int): Int  = this.resources.getDimensionPixelSize(dimen)

fun Context.hasNetworkAvailable(): Boolean {
    val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    return (manager?.activeNetworkInfo != null)
}
