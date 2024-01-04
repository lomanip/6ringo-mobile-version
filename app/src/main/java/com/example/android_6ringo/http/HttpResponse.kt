package com.example.android_6ringo.http

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import okhttp3.Response
import java.nio.charset.Charset

data class HttpResponse(private var response: Response, var objectMapper: ObjectMapper) {

    private var _bodyString: String? = null

    inline fun <reified T:Any>bodyAs(): T? {
        val value = getBodyString()
        if(value.isNullOrEmpty()) return null
        return objectMapper.readValue(value)
    }

    fun getBodyString(): String? {
        if(_bodyString == null) {
            val source = response.body!!.source()
            source.request(Long.MAX_VALUE)
            _bodyString = source.buffer.clone().readString(Charset.forName("UTF-8"))

        }
        return _bodyString
    }
}