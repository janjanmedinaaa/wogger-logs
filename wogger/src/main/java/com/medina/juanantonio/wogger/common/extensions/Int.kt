package com.medina.juanantonio.wogger.common.extensions

import android.util.Log

fun Int.getLogType(): String {
    return when (this) {
        Log.VERBOSE -> "VERBOSE"
        Log.DEBUG -> "DEBUG"
        Log.INFO -> "INFO"
        Log.WARN -> "WARD"
        Log.ERROR -> "ERROR"
        else -> "WTF"
    }
}