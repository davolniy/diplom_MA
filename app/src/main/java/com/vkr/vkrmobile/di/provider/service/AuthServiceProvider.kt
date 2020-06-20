package com.vkr.vkrmobile.di.provider.service

import com.vkr.vkrmobile.model.data.net.service.AuthService
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Provider

class AuthServiceProvider @Inject constructor(
    private val retrofit: Retrofit
) : Provider<AuthService> {

    override fun get(): AuthService = retrofit.create(
        AuthService::class.java)
}
