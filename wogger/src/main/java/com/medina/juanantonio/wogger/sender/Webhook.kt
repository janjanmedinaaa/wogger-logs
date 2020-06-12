package com.medina.juanantonio.wogger.sender

import android.content.Context
import android.util.Log
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.TextHttpResponseHandler
import com.medina.juanantonio.wogger.common.Constants.ContentType
import com.medina.juanantonio.wogger.common.Constants.Params
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.entity.StringEntity
import cz.msebera.android.httpclient.message.BasicHeader
import cz.msebera.android.httpclient.protocol.HTTP
import org.json.JSONArray
import org.json.JSONObject

class Webhook(
    private val context: Context?,
    private val webhookUrl: String
) {

    companion object {
        private const val TAG = "WOGGER_WEBHOOK"
    }

    fun send(receiver: String, messages: Array<String>) {
        val client = AsyncHttpClient()
        val params = JSONObject().apply {
            put(Params.RECEIVER, receiver)
            put(Params.MESSAGES, JSONArray(messages))
        }

        Log.d(TAG, params.toString())

        val headers = StringEntity(params.toString())
        headers.contentType = BasicHeader(HTTP.CONTENT_TYPE, ContentType.JSON)

        client.post(
            context,
            webhookUrl,
            headers,
            ContentType.JSON,
            object : TextHttpResponseHandler() {
                override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseString: String?
                ) {
                    Log.i(TAG, "Response Code: $statusCode")
                    Log.i(TAG, "Response String: $responseString")
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseString: String?,
                    throwable: Throwable?
                ) {
                    Log.e(TAG, "Response Code: $statusCode")
                    Log.e(TAG, "Response String: $responseString")
                    Log.e(TAG, "Throwable: $throwable")
                }
            })
    }
}