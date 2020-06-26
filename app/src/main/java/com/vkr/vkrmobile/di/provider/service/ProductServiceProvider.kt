package com.vkr.vkrmobile.di.provider.service

import com.vkr.vkrmobile.model.data.net.service.ProductService
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Provider

class ProductServiceProvider @Inject constructor(
    private val retrofit: Retrofit
) : Provider<ProductService> {

    override fun get(): ProductService = retrofit.create(
        ProductService::class.java)
}
