package com.synerzip.feeds.extentions

import com.synerzip.feeds.model.Common

fun List<Common>.getMaxHeightLink() : String {
    //return this.maxBy { it.attributes.height }?.label ?: ""
    return filter { it.attributes.height=="100" }[0].label
}