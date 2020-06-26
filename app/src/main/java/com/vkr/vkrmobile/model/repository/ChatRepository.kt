package com.vkr.vkrmobile.model.repository

import com.vkr.vkrmobile.model.data.net.service.CartService
import com.vkr.vkrmobile.model.data.net.service.ChatService
import com.vkr.vkrmobile.ui.global.fetchData
import com.vkr.vkrmobile.ui.global.fetchResult
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ChatRepository @Inject constructor(
    private val chatService: ChatService
){
    fun getChats() = chatService.getChats()
        .fetchData()
        .doOnSuccess { }
        .doOnError { }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun getChatMessages(chatId: Long, page: Int, pageSize: Int) = chatService.getChatMessages(chatId, page, pageSize)
        .fetchData()
        .doOnSuccess { }
        .doOnError { }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun sendMessage(chatId: Long, message: String) = chatService.sendMessage(chatId, message)
        .fetchResult()
        .doOnComplete {  }
        .doOnError { }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}