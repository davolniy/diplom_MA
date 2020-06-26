package com.vkr.vkrmobile.di.provider.service

import com.vkr.vkrmobile.model.data.net.service.CartService
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Provider

class CartServiceProvider @Inject constructor(
    private val retrofit: Retrofit
) : Provider<CartService> {

    override fun get(): CartService = retrofit.create(
        CartService::class.java)
}
