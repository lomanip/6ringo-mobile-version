package com.example.android_6ringo.entities

import java.time.ZonedDateTime

class Article {
    var createAt = ZonedDateTime.now()
    var updateAt = ZonedDateTime.now()
    var id = ""
    var name = ""
    var description = ""
    var price = 0
    var currency: String? = ""

    var images = listOf<Image>()
    var coverUrl = ""
    var familyId = ""
    var verificationLink = ""
    var brandNewLevel = ""
    var serialNumber = ""
    var certification = ""
}