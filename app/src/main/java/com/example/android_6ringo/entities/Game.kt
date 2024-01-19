package com.example.android_6ringo.entities

import java.time.ZonedDateTime

class Game {
    var createAt = ZonedDateTime.now()
    var updateAt = ZonedDateTime.now()

    var id: String = ""
    var _id: String = ""
    var name: String = ""
    var description: String? = null
    var category  = ""
    var price: Int = 0
    var currency: String? = null
    var status = ""
    var type = ""
    var startDate = ZonedDateTime.now()
    var endDate = ZonedDateTime.now()

    var article: Article? = null
    var articleId = ""


}