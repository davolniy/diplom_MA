package com.vkr.vkrmobile.model.data.net.response.product

import com.google.gson.annotations.SerializedName

class ProductResponse (
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("logo") val logo: String,
    @SerializedName("cost") val cost: Float,
    @SerializedName("duration") val duration: Long?
)