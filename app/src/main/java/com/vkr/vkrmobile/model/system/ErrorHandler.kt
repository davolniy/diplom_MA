package com.vkr.vkrmobile.model.system

import com.vkr.vkrmobile.model.data.net.ApiError
import com.vkr.vkrmobile.model.data.net.ServerError
import java.io.IOException
import javax.inject.Inject

class ErrorHandler @Inject constructor() {
    fun proceed(error: Throwable, messageListener: (String) -> Unit = {}) {
        when (error) {
            is ServerError -> {
                messageListener(error.userMessage())
            }
            is ApiError -> {
                error.errorMessage?.let { messageListener.invoke(it) }
            }
            else -> {
                messageListener.invoke(error.userMessage())
            }
        }
    }

    private fun Throwable.userMessage() =
        when (this) {
            is IOException -> "Нет соединения с интернетом"
            else -> message.orEmpty()
        }
}
