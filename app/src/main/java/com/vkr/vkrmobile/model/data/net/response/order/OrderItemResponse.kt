package com.vkr.vkrmobile.model.data.net.response.order

import com.google.gson.annotations.SerializedName
import com.vkr.vkrmobile.model.data.net.response.product.ProductResponse

class OrderItemResponse (
    @SerializedName("productData") val productData: ProductResponse,
    @SerializedName("count") val count: Long
)