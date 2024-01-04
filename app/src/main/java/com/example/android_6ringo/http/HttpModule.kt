package com.example.android_6ringo.http

import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.internal.EMPTY_REQUEST

typealias QueryParams = HashMap<String, *>


class HttpClient(private val okClient: OkHttpClient, private val objectMapper: ObjectMapper) {

    suspend fun get(url: String, params: QueryParams = QueryParams()): HttpResponse {
        val httpUrl = buildUrl(url, params)

        val request = Request.Builder().url(httpUrl).build()

        return withContext(Dispatchers.IO) {
            val okResponse = okClient.newCall(request).execute()
            HttpResponse(okResponse, objectMapper)
        }
    }

    suspend fun post(httpUrl: String, body: Any, params: QueryParams = QueryParams()): HttpResponse {
        return withContext(Dispatchers.IO) {
            val url = buildUrl(httpUrl, params)
            val requestBody = objectMapper.requestBody(body)
            val request = Request.Builder().url(url)
                .post(requestBody)
                .build()
            val okResponse = okClient.newCall(request).execute()
            HttpResponse(okResponse, objectMapper)
        }
    }

    suspend fun post(httpUrl: String, requestBody: RequestBody, params: QueryParams = QueryParams()): HttpResponse {
        return withContext(Dispatchers.IO) {
            val url = buildUrl(httpUrl, params)
            val request = Request.Builder().url(url)
                .post(requestBody)
                .build()
            val okResponse = okClient.newCall(request).execute()
            HttpResponse(okResponse, objectMapper)
        }
    }

    suspend fun put(url: String, requestBody: RequestBody, params: QueryParams = QueryParams()): HttpResponse {
        return withContext(Dispatchers.IO) {
            val httpUrl = buildUrl(url, params)
            val request = Request.Builder().url(httpUrl)
                .put(requestBody)
                .build()
            val okResponse = okClient.newCall(request).execute()
            HttpResponse(okResponse, objectMapper)
        }
    }


    suspend fun delete(url: String, params: QueryParams = QueryParams()): HttpResponse {
        return withContext(Dispatchers.IO) {
            val httpUrl = buildUrl(url, params)
            val request = Request.Builder().url(httpUrl)
                .delete(EMPTY_REQUEST)
                .build()
            val okResponse = okClient.newCall(request).execute()
            HttpResponse(okResponse, objectMapper)
        }
    }


    suspend fun delete(url: String, requestBody: RequestBody, params: QueryParams = QueryParams()): HttpResponse {
        return withContext(Dispatchers.IO) {
            val httpUrl = buildUrl(url, params)
            val request = Request.Builder().url(httpUrl)
                .delete(requestBody)
                .build()
            val okResponse = okClient.newCall(request).execute()
            HttpResponse(okResponse, objectMapper)
        }
    }

    private fun buildUrl(url: String, params: HashMap<String, *>): String {
        var urlBuilder = url.toHttpUrl().newBuilder()
        params.forEach {entry ->
            urlBuilder = urlBuilder.addEncodedQueryParameter(entry.key, entry.value.toString())
        }
        return urlBuilder.build().toString()
    }
}

fun ObjectMapper.requestBody(value: Any): RequestBody {
    val bodyValue = this.writeValueAsString(value)
    return bodyValue.toRequestBody("application/json".toMediaTypeOrNull())
}