package com.vkr.vkrmobile.di.provider.service

import com.vkr.vkrmobile.model.data.net.service.ServiceService
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Provider

class ServiceServiceProvider @Inject constructor(
    private val retrofit: Retrofit
) : Provider<ServiceService> {

    override fun get(): ServiceService = retrofit.create(
        ServiceService::class.java)
}
