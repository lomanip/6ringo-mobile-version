package com.example.android_6ringo

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.DI
import org.kodein.di.bindSingleton

fun setupApplicationService(): DI {
    val objectMapper = ObjectMapper()
    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)

    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    val okHttpClient= OkHttpClient()
        .newBuilder()
        .addInterceptor(loggingInterceptor)
        .build()

    val kodein = DI {
        bindSingleton { okHttpClient }
        bindSingleton { objectMapper }
    }

    return kodein
}