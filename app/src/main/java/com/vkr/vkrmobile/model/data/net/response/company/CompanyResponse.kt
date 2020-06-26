package com.vkr.vkrmobile.model.data.net.response.company

import com.google.gson.annotations.SerializedName

class CompanyResponse (
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("address") val address: String,
    @SerializedName("logo") val logo: String,
    @SerializedName("parentId") val parentId: Long?,
    @SerializedName("reviewScore") val reviewScore: Long
)