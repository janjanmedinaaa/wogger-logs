package com.medina.juanantonio.wogger.data.listeners

import com.medina.juanantonio.wogger.data.models.WoggerLog

interface LogListener {
    fun onLogCreated(log: WoggerLog) {}
    fun onLogListUpdated(logs: Array<WoggerLog>) {}
}