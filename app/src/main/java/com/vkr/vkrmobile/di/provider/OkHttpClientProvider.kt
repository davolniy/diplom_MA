package com.vkr.vkrmobile.di.provider

import com.vkr.vkrmobile.model.data.auth.AuthHolder
import com.vkr.vkrmobile.model.data.net.interceptor.AuthInterceptor
import com.vkr.vkrmobile.model.data.net.interceptor.ErrorResponseInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Provider

class OkHttpClientProvider @Inject constructor(
    private val authData: AuthHolder
) : Provider<OkHttpClient> {

    override fun get(): OkHttpClient = with(OkHttpClient.Builder()) {
        connectTimeout(30, TimeUnit.SECONDS)
        readTimeout(30, TimeUnit.SECONDS)

        addNetworkInterceptor(AuthInterceptor(authData))
        addNetworkInterceptor(ErrorResponseInterceptor())
        build()
    }
}
