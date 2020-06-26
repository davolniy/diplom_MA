package com.vkr.vkrmobile.model.data.net.response.request

import com.google.gson.annotations.SerializedName

class RequestTypeDynamicFieldResponse (
    @SerializedName("id") val id: Long,
    @SerializedName("dynamicFieldType") val dynamicFieldType: String,
    @SerializedName("name") val name: String
)