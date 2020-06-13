package com.medina.juanantonio.wogger.common.extensions

import android.content.Context

fun Context.getApplicationName(): String {
    val appInfo = applicationInfo
    val stringId = appInfo.labelRes

    return if (stringId == 0) applicationInfo.nonLocalizedLabel.toString()
    else getString(stringId)

}