package com.example.android_6ringo.models

class PagingResult<T> {

    var metaData = PagingResultMetaData()
    var data = listOf<T>()
}

class PagingResultMetaData {
    var total = 0
    var page = 0
    var pageSize = 0
    var hasNextPage = false
}

data class PagingOptions(
    var orderBy: String = "",
    var page: Int = 0,
    var limit: Int = 0
)