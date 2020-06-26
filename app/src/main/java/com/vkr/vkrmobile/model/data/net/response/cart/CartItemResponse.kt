package com.vkr.vkrmobile.model.data.net.response.cart

import com.google.gson.annotations.SerializedName
import com.vkr.vkrmobile.model.data.net.response.product.ProductResponse

class CartItemResponse (
    @SerializedName("productData") val productData: ProductResponse,
    @SerializedName("count") val count: Long
)