package com.synerzip.feeds.extentions

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.getColumnCountOnOrientation(context: Context): Int {
    return if(context.isOrientationPortrait()) 3 else 5
}