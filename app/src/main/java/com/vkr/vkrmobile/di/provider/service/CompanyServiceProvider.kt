package com.vkr.vkrmobile.di.provider.service

import com.vkr.vkrmobile.model.data.net.service.CompanyService
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Provider

class CompanyServiceProvider @Inject constructor(
    private val retrofit: Retrofit
) : Provider<CompanyService> {

    override fun get(): CompanyService = retrofit.create(
        CompanyService::class.java)
}
