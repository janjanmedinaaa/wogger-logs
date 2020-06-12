package com.medina.juanantonio.wogger

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.databinding.ObservableArrayList
import com.medina.juanantonio.wogger.data.models.WoggerLog
import com.medina.juanantonio.wogger.sender.Messenger
import com.medina.juanantonio.wogger.sender.SMS
import com.medina.juanantonio.wogger.sender.Webhook

object Wogger {

    var owner: Context? = null
    var platform: WoggerPlatform? = null
    var receiver: String? = null
    var webhook: String? = null

    var logs = ObservableArrayList<WoggerLog>()

    private var tag: String = ""
    private var msg: String = ""

    fun d(tag: String, msg: String): Wogger {
        setData(tag, msg, Log.DEBUG)
        Log.d(tag, msg)
        return this
    }

    fun e(tag: String, msg: String): Wogger {
        setData(tag, msg, Log.ERROR)
        Log.e(tag, msg)
        return this
    }

    fun i(tag: String, msg: String): Wogger {
        setData(tag, msg, Log.INFO)
        Log.i(tag, msg)
        return this
    }

    fun v(tag: String, msg: String): Wogger {
        setData(tag, msg, Log.VERBOSE)
        Log.v(tag, msg)
        return this
    }

    fun w(tag: String, msg: String): Wogger {
        setData(tag, msg, Log.WARN)
        Log.w(tag, msg)
        return this
    }

    fun wtf(tag: String, msg: String): Wogger {
        setData(tag, msg, 99)
        Log.wtf(tag, msg)
        return this
    }

    fun send(
        receiver: String? = null,
        platform: WoggerPlatform? = null,
        send: Boolean = true
    ) {
        if (!send) return
        val sendTo = receiver ?: this.receiver

        when (platform ?: this.platform) {
            WoggerPlatform.MESSENGER -> {
                Messenger(owner).send(
                    sendTo ?: return,
                    arrayOf(formatText(msg))
                )
            }
            WoggerPlatform.SMS -> {
                SMS(owner).send(
                    sendTo ?: return,
                    arrayOf(formatText(msg))
                )
            }
            WoggerPlatform.WEBHOOK -> {
                Webhook(owner, webhook ?: return).send(
                    sendTo ?: "",
                    arrayOf(formatText(msg))
                )
            }
        }
    }

    private fun setData(tag: String, msg: String, type: Int) {
        logs.add(WoggerLog(type, msg))
        this.tag = tag
        this.msg = msg
    }

    @SuppressLint("DefaultLocale")
    private fun formatText(msg: String): String {
        return "Device Name: ${Build.MANUFACTURER.capitalize()} ${Build.MODEL} \n" +
                "SDK Version: ${Build.VERSION.SDK_INT}\n" +
                "Brand: ${Build.BRAND.capitalize()}\n\n$msg"
    }
}

enum class WoggerPlatform {
    MESSENGER,
    SMS,
    WEBHOOK
}