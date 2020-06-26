package com.vkr.vkrmobile.model.interactor

import com.vkr.vkrmobile.model.repository.ChatRepository
import javax.inject.Inject

class ChatInteractor @Inject constructor(
    private val chatRepository: ChatRepository
){
    fun getChats() = chatRepository.getChats()

    fun getChatMessages(chatId: Long, page: Int, pageSize: Int) = chatRepository.getChatMessages(chatId, page, pageSize)

    fun sendMessage(chatId: Long, message: String) = chatRepository.sendMessage(chatId, message)
}