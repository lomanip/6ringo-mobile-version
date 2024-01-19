package com.example.android_6ringo

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.android_6ringo.auth.services.AccessTokenInterceptor
import com.example.android_6ringo.auth.services.AuthDataStore
import com.example.android_6ringo.auth.services.AuthService
import com.example.android_6ringo.http.HttpClient
import com.example.android_6ringo.http.HttpErrorInterceptor
import com.example.android_6ringo.services.GameService
import com.example.android_6ringo.services.ShoppingCartService
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
    val networkErrorLive = MutableLiveData<Exception>()
    fun setupApplicationService(context: Context): Container {
        val authDataStore = AuthDataStore(context)

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

        val accessTokenInterceptor = AccessTokenInterceptor(authDataStore, objectMapper)
        val connectInterceptor = HttpErrorInterceptor(networkErrorLive)

        val okHttpClient= OkHttpClient()
            .newBuilder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(accessTokenInterceptor)
            .addInterceptor(connectInterceptor)
            .build()




        val kodein = DI {
            bindSingleton { okHttpClient }
            bindSingleton { objectMapper }
            bindSingleton { GameService(instance()) }
            bindSingleton { ShoppingCartService(instance()) }

            bindProvider<HttpClient> { HttpClient(instance(), instance()) }

            bindSingleton { AuthService(instance(), instance(), authDataStore) }
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