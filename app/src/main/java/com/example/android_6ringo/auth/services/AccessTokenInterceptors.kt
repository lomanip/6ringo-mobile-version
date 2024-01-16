package com.example.android_6ringo.auth.services

import android.util.Log
import com.example.android_6ringo.auth.models.SignInResultModel
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import okhttp3.Interceptor
import okhttp3.Response

class AccessTokenInterceptor(
    private var authDataStore: AuthDataStore,
    private var objectMapper: ObjectMapper
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val sharedPreferences = authDataStore.sharedPreferences
        val authDataValue = sharedPreferences.getString(AUTH_DATA_KEY, "")


        if (authDataValue != "") {
            val authData = objectMapper.readValue<SignInResultModel>(authDataValue!!)
            val request = chain.request().newBuilder()
                .header("Authorization", "Bearer ${authData.token.accessToken}")
                .build()
            return chain.proceed(request)
        }
        return chain.proceed(chain.request())
    }
}