package com.medina.juanantonio.wogger.data.models

import java.util.Date

data class WoggerLog(
    val type: Int,
    val msg: String = "",
    val timestamp: Date = Date()
)