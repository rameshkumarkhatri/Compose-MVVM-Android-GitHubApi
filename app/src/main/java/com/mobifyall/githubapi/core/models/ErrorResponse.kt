package com.mobifyall.githubapi.core.models


data class ErrorResponse(
    val message: String? = null,
    val errors: List<Errors>? = null,
) {
    fun getMessageText(): String {
        return (errors?.firstOrNull()?.message ?: message).orEmpty()
    }
}

data class Errors(
    val message: String? = null
)
