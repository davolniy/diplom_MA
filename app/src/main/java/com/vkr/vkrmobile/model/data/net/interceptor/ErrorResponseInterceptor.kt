package com.vkr.vkrmobile.model.data.net.interceptor

import com.vkr.vkrmobile.model.data.net.ServerError
import okhttp3.Interceptor
import okhttp3.Response

class ErrorResponseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        val code = response.code()
        if (code in 400..500) throw ServerError(code)

        return response

    }
}

