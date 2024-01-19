package com.example.android_6ringo.auth.models

class SignUpModel {
    var email: String = ""
    var password: String = ""
    var username: String = ""
    var phone: String = ""
}

class SignUpResultModel {
    var id: String = ""
    var email: String = ""
    var provider: String = ""
    var username: String = ""
    var firstName: String = ""
    var lastName: String = ""
    var phone: String = ""
    var roles = listOf<String>()
    var resetKey: String = ""
    var resetCount = 0
    var resetTimestamp: String = ""
    var resetKeyTimestamp: String = ""
    var emailVerified = false
    var verifyKey: String = ""
    var verifyTimestamp: String = ""
    var verifyKeyTimestamp: String = ""
    var approved = false
}
