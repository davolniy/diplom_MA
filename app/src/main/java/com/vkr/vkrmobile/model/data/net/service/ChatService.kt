package com.vkr.vkrmobile.model.data.net.service

import com.vkr.vkrmobile.model.data.ApiMethods
import com.vkr.vkrmobile.model.data.net.ApiResponse
import com.vkr.vkrmobile.model.data.net.ApiResponseEmpty
import com.vkr.vkrmobile.model.data.net.response.chat.ChatResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ChatService {
    @GET(ApiMethods.Chats.GetChats)
    fun getChats(): Single<ApiResponse<List<ChatResponse>>>

    @GET(ApiMethods.Chats.GetChatMessages)
    fun getChatMessages(
        @Query("chatId") chatId: Long,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Single<ApiResponse<ChatResponse>>

    @POST(ApiMethods.Chats.SendMessage)
    fun sendMessage(
        @Query("chatId") chatId: Long,
        @Query("message") message: String
    ): Single<ApiResponseEmpty>
}