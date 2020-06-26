package com.vkr.vkrmobile.di.provider.service

import com.vkr.vkrmobile.model.data.net.service.ReviewService
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Provider

class ReviewServiceProvider @Inject constructor(
    private val retrofit: Retrofit
) : Provider<ReviewService> {

    override fun get(): ReviewService = retrofit.create(
        ReviewService::class.java)
}
