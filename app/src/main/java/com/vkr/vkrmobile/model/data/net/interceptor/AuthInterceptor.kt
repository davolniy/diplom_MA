package com.vkr.vkrmobile.model.data.net.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import ru.feedback.app.model.data.auth.AuthHolder

class AuthInterceptor constructor(
    private val authHolder: AuthHolder
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = authHolder.token?.let {
            chain.request()
                .newBuilder()
                .addHeader("Authorization", it)
                .addHeader("Accept", "application/json")
                .build()
        } ?: chain.request().let {
            val url = it.url().toString()
            it.newBuilder()
                .url(url)
                .build()
        }

        return chain.proceed(request)
    }
}
