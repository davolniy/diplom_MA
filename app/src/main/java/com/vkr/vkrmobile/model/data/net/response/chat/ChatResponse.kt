package com.vkr.vkrmobile.model.data.net.response.chat

import com.google.gson.annotations.SerializedName

class ChatResponse (
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("logo") val logo: String?,
    @SerializedName("companyId") val companyId: Long,
    @SerializedName("messages") val messages: List<ChatMessageResponse>,
    @SerializedName("lastMessage") val lastMessage: ChatMessageResponse
)