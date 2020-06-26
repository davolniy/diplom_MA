package com.vkr.vkrmobile.model.data.net.response.order

import com.google.gson.annotations.SerializedName
import java.util.*

class OrderResponse (
    @SerializedName("id") val id: Long,
    @SerializedName("status") val name: String,
    @SerializedName("cost") val cost: Float,
    @SerializedName("companyName") val companyName: String,
    @SerializedName("createDate") val createDate: Date,
    @SerializedName("items") val items: List<OrderItemResponse>
)