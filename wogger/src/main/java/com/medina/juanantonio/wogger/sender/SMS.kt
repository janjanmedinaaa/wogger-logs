package com.medina.juanantonio.wogger.sender

import android.content.Context
import android.util.Log
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
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
            object : JsonHttpResponseHandler() {
                override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    response: JSONArray?
                ) {
                    super.onSuccess(statusCode, headers, response)
                    Log.e(TAG, "Response Code: $statusCode")
                    Log.e(TAG, "Response String: $response")
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseString: String?,
                    throwable: Throwable?
                ) {
                    super.onFailure(statusCode, headers, responseString, throwable)
                    Log.e(TAG, "Response Code: $statusCode")
                    Log.e(TAG, "Response String: $responseString")
                    Log.e(TAG, "Throwable: $throwable")
                }
            })
    }
}