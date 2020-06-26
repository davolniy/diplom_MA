package com.vkr.vkrmobile.model.data.net.response.request

import com.google.gson.annotations.SerializedName

class DynamicField (
    @SerializedName("id") val id: Long,
    @SerializedName("value") val value: String,
    @SerializedName("typeId") val typeId: Long
)