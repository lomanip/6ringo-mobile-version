package com.example.android_6ringo.helpers

import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

fun <T : Any> T.toQueryParams(): Map<String, Any?> {
    return (this::class as KClass<T>).memberProperties.associate { prop ->
        prop.name to prop.get(this)?.let { value ->
            value
        }
    }
}