package com.vkr.vkrmobile.model.data.net.response.request

import com.google.gson.annotations.SerializedName

class RequestTyoeResponse (
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("companyId") val companyId: Long,
    @SerializedName("dynamicFieldTypes") val dynamicFieldTypes: List<RequestTypeDynamicFieldResponse>
)