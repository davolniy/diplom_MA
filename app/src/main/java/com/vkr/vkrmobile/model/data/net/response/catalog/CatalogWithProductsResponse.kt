package com.vkr.vkrmobile.model.data.net.response.catalog

import com.google.gson.annotations.SerializedName
import com.vkr.vkrmobile.model.data.net.response.product.ProductResponse

class CatalogWithProductsResponse(
    @SerializedName("catalogData") val catalog: CatalogResponse,
    @SerializedName("catalogProducts") val catalogProducts: List<ProductResponse>
)