package com.medina.juanantonio.woggerlogs

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.medina.juanantonio.wogger.Wogger
import com.medina.juanantonio.wogger.WoggerPlatform
import com.medina.juanantonio.wogger.common.extensions.getLogType
import com.medina.juanantonio.wogger.data.listeners.LogListener
import com.medina.juanantonio.wogger.data.models.WoggerLog
import com.medina.juanantonio.woggerlogs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), LogListener {

    companion object {
        const val TAG = "WOGGER_LOGS"
        const val WOGGER_MESSENGER_RECEIVER = "2846280848828368"
    }

    private lateinit var binding: ActivityMainBinding
    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Set the Global Config
        Wogger.apply {
            owner = this@MainActivity
            receiver = WOGGER_MESSENGER_RECEIVER
            platform = WoggerPlatform.MESSENGER
            listener = this@MainActivity
        }

        // Send the Log Message
        Wogger.i(TAG, "WoggerLogs App Started").send()

        binding.buttonAdd.setOnClickListener {
            counter++
            Wogger.i(TAG, "Add Button Clicked $counter times").send()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onLogCreated(log: WoggerLog) {
        val currentText = binding.textviewLogs.text
        binding.textviewLogs.text = "$currentText\n${log.type.getLogType()}: ${log.msg}"
    }
}
