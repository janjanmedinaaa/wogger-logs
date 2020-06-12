package com.medina.juanantonio.woggerlogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.medina.juanantonio.wogger.Wogger
import com.medina.juanantonio.wogger.WoggerPlatform

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "WOGGER_LOGS"

        const val WOGGER_MESSENGER_RECEIVER = ""
        const val WOGGER_SMS_RECEIVER = ""
        const val WOGGER_CUSTOM_WEBHOOK = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set the Global Config
        Wogger.apply {
            owner = this@MainActivity
            receiver = WOGGER_MESSENGER_RECEIVER
            platform = WoggerPlatform.MESSENGER
            webhook = WOGGER_CUSTOM_WEBHOOK
        }

        // Simple Log Message
        Wogger.d(TAG, "Test Line 30")

        // Send the Log Message to Globally Set Platform and Receiver
        Wogger.i(TAG, "Running on Line 33").send()

        // Send the Log Message based on a Condition
        Wogger.e(TAG, "Bug on Line 25").send(send = BuildConfig.DEBUG)

        // Send the Log Message with a Custom Platform and Receiver
        Wogger.w(TAG, "Warn me through SMS").send(
            receiver = WOGGER_SMS_RECEIVER,
            platform = WoggerPlatform.SMS
        )

        // Send the Log Message to a Custom Webhook
        Wogger.wtf(TAG, "Send this to my Webhook").send(
            platform = WoggerPlatform.WEBHOOK
        )
    }
}
