package com.vkr.vkrmobile.model.data.net

data class ApiError(
    val errorCode: Int,
    val errorMessage: String?
) : RuntimeException()
