package com.vkr.vkrmobile.model.data.net.response.news

import com.google.gson.annotations.SerializedName

class NewsResponse (
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("text") val text: String,
    @SerializedName("logo") val logo: String
)