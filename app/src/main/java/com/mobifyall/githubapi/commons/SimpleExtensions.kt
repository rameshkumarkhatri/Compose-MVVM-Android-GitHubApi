package com.mobifyall.githubapi.commons

import com.google.gson.Gson


fun String?.getString() = this ?: ""

fun emptyString() = ""

fun Int?.getIntOrZero() = this ?: 0

fun Any?.convertToJson(): String {
    return if (this == null) emptyString()
    else {
        Gson().toJson(this)
    }
}
