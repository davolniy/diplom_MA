package com.vkr.vkrmobile.model.data.net.response.catalog

import com.google.gson.annotations.SerializedName

class CatalogResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: Long,
    @SerializedName("companyId") val companyId: Long
)