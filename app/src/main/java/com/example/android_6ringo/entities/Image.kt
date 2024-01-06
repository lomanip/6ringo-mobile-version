package com.example.android_6ringo.entities

import java.time.ZonedDateTime

class Image {
    var id = ""
    var createAt = ZonedDateTime.now()
    var name = ""
    var mimeType = ""
    var fileUrl = ""
    var size = 0
    var thumbnailPath = ""
    var thumbnailUrl = ""


}