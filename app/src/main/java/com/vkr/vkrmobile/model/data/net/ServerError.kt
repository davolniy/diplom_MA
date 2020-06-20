package ru.feedback.app.model.data.net

data class ServerError(val errorCode: Int) : RuntimeException()
