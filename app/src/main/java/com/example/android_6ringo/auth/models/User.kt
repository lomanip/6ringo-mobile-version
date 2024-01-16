package com.example.android_6ringo.auth.models

import java.time.LocalDate

class User {
    var id = ""
    var email = ""
    var provider = ""
    var username = ""
    var phone = ""
    var roles = listOf<String>()
    var resetKey = ""
    var emailVerified = false
    var verifyKey = ""
    var verifyKeyTimestamp = LocalDate.MIN
}