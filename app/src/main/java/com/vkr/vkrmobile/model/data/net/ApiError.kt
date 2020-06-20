package ru.feedback.app.model.data.net

data class ApiError(
    val errorCode: Int,
    val errorMessage: String?
) : RuntimeException()
