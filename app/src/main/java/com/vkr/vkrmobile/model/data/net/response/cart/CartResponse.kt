package com.vkr.vkrmobile.model.data.net.response.cart

import com.google.gson.annotations.SerializedName

class CartResponse (
    @SerializedName("id") val id: Long,
    @SerializedName("cost") val cost: Float,
    @SerializedName("name") val name: String,
    @SerializedName("companyName") val companyName: String,
    @SerializedName("companyId") val companyId: Long,
    @SerializedName("cartItems") val cartItems: List<CartItemResponse>
)