package com.vkr.vkrmobile.di.provider.service

import com.vkr.vkrmobile.model.data.net.service.CatalogService
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Provider

class CatalogServiceProvider @Inject constructor(
    private val retrofit: Retrofit
) : Provider<CatalogService> {

    override fun get(): CatalogService = retrofit.create(
        CatalogService::class.java)
}
