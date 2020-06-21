package com.vkr.vkrmobile.di.provider.service

import com.vkr.vkrmobile.model.data.net.service.NewsService
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Provider

class NewsServiceProvider @Inject constructor(
    private val retrofit: Retrofit
) : Provider<NewsService> {

    override fun get(): NewsService = retrofit.create(
        NewsService::class.java)
}
