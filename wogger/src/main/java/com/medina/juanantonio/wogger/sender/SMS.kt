package com.medina.juanantonio.wogger.sender

import android.content.Context
import android.util.Log
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.TextHttpResponseHandler
import com.medina.juanantonio.wogger.common.Constants.ContentType
import com.medina.juanantonio.wogger.common.Constants.Params
import com.medina.juanantonio.wogger.common.Constants.Wogger.WOGGER_SMS_URL
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.entity.StringEntity
import cz.msebera.android.httpclient.message.BasicHeader
import cz.msebera.android.httpclient.protocol.HTTP
import org.json.JSONArray
import org.json.JSONObject

class SMS(private val context: Context?) {

    companion object {
        private const val TAG = "WOGGER_SMS"
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
            WOGGER_SMS_URL,
            headers,
            ContentType.JSON,
            object : TextHttpResponseHandler() {
                override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseString: String?
                ) {
                    Log.d(TAG, "Response Code: $statusCode")
                    Log.d(TAG, "Response String: $responseString")
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseString: String?,
                    throwable: Throwable?
                ) {
                    Log.d(TAG, "Response Code: $statusCode")
                    Log.d(TAG, "Response String: $responseString")
                    Log.d(TAG, "Throwable: $throwable")
                }
            })
    }
}