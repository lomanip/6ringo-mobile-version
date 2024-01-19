package com.example.android_6ringo.auth.services

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.android_6ringo.BuildConfig
import com.example.android_6ringo.auth.models.Me
import com.example.android_6ringo.auth.models.SignInModel
import com.example.android_6ringo.auth.models.SignInResultModel
import com.example.android_6ringo.auth.models.SignUpModel
import com.example.android_6ringo.auth.models.SignUpResultModel
import com.example.android_6ringo.auth.models.User
import com.example.android_6ringo.http.HttpClient
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.time.ZonedDateTime


val AUTH_DATA_KEY = "AUTH_DATA"
val AUTH_USER_KEY = "AUTH_USER"
class AuthService(
    private val httpClient: HttpClient,
    private val objectMapper: ObjectMapper,
    private val authDataStore: AuthDataStore) {
    private var apiUrl = "${BuildConfig.SERVER_URL}/micro-auth"

    var user: User? = null
    var signInResult: SignInResultModel? = null
    var isInitState = MutableLiveData<Boolean>()

    suspend fun init() {
        val authData = authDataStore.sharedPreferences.getString(AUTH_DATA_KEY, "")
        if(authData == "") {
            this.isInitState.value = true
            return
        }
        signInResult = objectMapper.readValue(authData!!)!!

        if(signInResult!!.tokens.accessTokenExpiresAt.isBefore(ZonedDateTime.now())) {
            removeSignInData()
            this.isInitState.value = true
            return
        }

        user = refreshAuthUser(signInResult!!.id)

        isInitState.value = true
    }

    fun isLoggedIn():Boolean {
        return user != null
    }

    suspend fun signIn(model: SignInModel) {
        val url = "$apiUrl/signin"
        val result = httpClient.post(url, model).bodyAs<SignInResultModel>()!!

        Log.d(javaClass.name, result.tokens.accessToken)
        handleLoginResult(result)
    }

    suspend fun signUp(model: SignUpModel): String {
        val url = "$apiUrl/signup"
        val result = httpClient.post(url, model).bodyAs<String>()!!

        return result
    }

    fun signOut() {
        removeSignInData()
    }




    private suspend fun handleLoginResult(result: SignInResultModel) {
        // Do it before sa that interceptor can get access token to get user info
        with(authDataStore.sharedPreferences.edit()) {
            putString(AUTH_DATA_KEY, objectMapper.writeValueAsString(result))
            commit()
        }

        user = getUser(result.id)
        with(authDataStore.sharedPreferences.edit()) {
            putString(AUTH_USER_KEY, objectMapper.writeValueAsString(user))
            commit()
        }
        signInResult = result
    }

    private suspend fun refreshAuthUser(id: String): User {
        lateinit var currentUser: User
        val currentUserData = authDataStore.sharedPreferences.getString(AUTH_DATA_KEY, "")

        if(currentUserData != "") {
            currentUser = objectMapper.readValue(currentUserData!!)
        }
        try {
            currentUser = getUser(id)
            with(authDataStore.sharedPreferences.edit()) {
                putString(AUTH_USER_KEY, objectMapper.writeValueAsString(currentUser))
                commit()
            }
        }catch (ex: Exception) {
            Log.e(this.javaClass.name, "Error during get current user.", ex)
        }
        return currentUser
    }


    suspend fun getUser(id: String): User {
        val url = "$apiUrl/users/$id"
        return httpClient.get(url).bodyAs<User>()!!
    }

    suspend fun me(): Me {
        val url = "$apiUrl/users/me"
        return httpClient.get(url).bodyAs<Me>()!!
    }

    private fun removeSignInData() {
        with(authDataStore.sharedPreferences.edit()) {
            remove(AUTH_DATA_KEY)
            remove(AUTH_USER_KEY)
            commit()
        }
        user = null
        signInResult = null
    }
}