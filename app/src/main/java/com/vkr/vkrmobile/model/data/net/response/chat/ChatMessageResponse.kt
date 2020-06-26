package com.vkr.vkrmobile.model.data.net.response.chat

import com.google.gson.annotations.SerializedName
import java.util.*

class ChatMessageResponse (
    @SerializedName("id") val id: Long,
    @SerializedName("text") val text: Long,
    @SerializedName("chatId") val chatId: Long,
    @SerializedName("createDate") val createDate: Date,
    @SerializedName("senderId") val senderId: Long
)