package com.example.android_6ringo.http

import android.util.Log
import androidx.lifecycle.MutableLiveData
import okhttp3.Interceptor
import okhttp3.Response


class HttpErrorInterceptor(var liveData: MutableLiveData<Exception>): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        try {
            return chain.proceed(chain.request())
        }catch (ex: Exception) {
            liveData.postValue(ex)
            Log.e(javaClass.name, "Intercept error; type: ${ex.javaClass.name}", ex)
        }
        return chain.proceed(chain.request())
    }
}