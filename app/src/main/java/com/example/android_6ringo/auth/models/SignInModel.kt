package com.example.android_6ringo.auth.models

import java.time.ZonedDateTime

data class SignInModel(val email: String, val password: String)

class SignInResultModel {
    var id: String = ""
    var email: String = ""
    var approved: Boolean = false

    var roles = listOf<String>()
    var tokens = SignInToken()
}

class SignInToken {
    var accessToken: String = ""
    var expireIn: String = ""
    var refreshToken: String = ""
    var tokenId: String = ""
    var accessTokenExpiresAt = ZonedDateTime.now()
}

data class RefreshTokenModel(var refreshToken: String)
class RefreshTokenResult()

data class Me(var id: String, var email: String, var name: String)