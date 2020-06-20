package com.vkr.vkrmobile.model.data.net.response.launch

import com.google.gson.annotations.SerializedName

class ConfigurationFunctions (
    @SerializedName("news") val news: Boolean = true,
    @SerializedName("actions") val actions: Boolean = false,
    @SerializedName("chats") val chats: Boolean = false,
    @SerializedName("requests") val requests: Boolean = false,
    @SerializedName("reviews") val reviews: Boolean = false,
    @SerializedName("catalogs") val catalogs: Boolean = false,
    @SerializedName("services") val services: Boolean = false,
    @SerializedName("orders") val orders: Boolean = false
)