package com.vkr.vkrmobile.di.provider.service

import com.vkr.vkrmobile.model.data.net.service.RequestService
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Provider

class RequestServiceProvider @Inject constructor(
    private val retrofit: Retrofit
) : Provider<RequestService> {

    override fun get(): RequestService = retrofit.create(
        RequestService::class.java)
}
