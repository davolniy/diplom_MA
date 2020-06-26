package com.vkr.vkrmobile.di.provider.service

import com.vkr.vkrmobile.model.data.net.service.ChatService
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Provider

class ChatServiceProvider @Inject constructor(
    private val retrofit: Retrofit
) : Provider<ChatService> {

    override fun get(): ChatService = retrofit.create(
        ChatService::class.java)
}
