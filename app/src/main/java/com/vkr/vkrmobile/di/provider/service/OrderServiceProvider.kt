package com.vkr.vkrmobile.di.provider.service

import com.vkr.vkrmobile.model.data.net.service.OrderService
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Provider

class OrderServiceProvider @Inject constructor(
    private val retrofit: Retrofit
) : Provider<OrderService> {

    override fun get(): OrderService = retrofit.create(
        OrderService::class.java)
}
