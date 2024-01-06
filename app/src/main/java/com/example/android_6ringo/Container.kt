package com.example.android_6ringo

import android.content.Context
import com.example.android_6ringo.http.HttpClient
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.util.StdDateFormat
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.DI
import org.kodein.di.bindFactory
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.TimeZone

class Container  {
    lateinit var kodein : DI
    fun setupApplicationService(): Container {
        val objectMapper = ObjectMapper()
        objectMapper.configure(SerializationFeature.WRITE_DATES_WITH_CONTEXT_TIME_ZONE, true)
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        objectMapper.dateFormat = StdDateFormat().withColonInTimeZone(true)
            .withTimeZone(TimeZone.getTimeZone("UTC"))

        val timeModule = JavaTimeModule()
        timeModule.addDeserializer(
            LocalDateTime::class.java, LocalDateTimeDeserializer(
                DateTimeFormatter.ISO_DATE_TIME
            )
        )
        objectMapper.registerModules(timeModule)

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient= OkHttpClient()
            .newBuilder()
            .addInterceptor(loggingInterceptor)
            .build()

        val kodein = DI {
            bindSingleton { okHttpClient }
            bindSingleton { objectMapper }
            bindSingleton { GameService(instance()) }

            bindProvider<HttpClient> { HttpClient(instance(), instance()) }
        }
        this.kodein = kodein
        return this
    }
}

fun Context.container(): Container {
    if(Ringo6Application::class.java == this.applicationContext.javaClass) {
        return (this.applicationContext as Ringo6Application).container
    }
    throw IllegalStateException("This context is not Ringo6Application, but: ${this.applicationContext.javaClass}.")
}