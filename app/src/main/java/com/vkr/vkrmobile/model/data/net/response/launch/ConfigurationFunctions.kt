package com.vkr.vkrmobile.model.data.net.response.launch

import com.google.gson.annotations.SerializedName

class ConfigurationFunctions (
    @SerializedName("News") val news: Boolean = true,
    @SerializedName("Actions") val actions: Boolean = false,
    @SerializedName("Chats") val chats: Boolean = false,
    @SerializedName("Requests") val requests: Boolean = false,
    @SerializedName("Reviews") val reviews: Boolean = false,
    @SerializedName("Catalogs") val catalogs: Boolean = false,
    @SerializedName("Services") val services: Boolean = false,
    @SerializedName("Orders") val orders: Boolean = false
)