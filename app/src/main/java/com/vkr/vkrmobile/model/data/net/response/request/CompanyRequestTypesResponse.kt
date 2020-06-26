package com.vkr.vkrmobile.model.data.net.response.request

import com.google.gson.annotations.SerializedName

class CompanyRequestTypesResponse(
    @SerializedName("companyName") val companyName: String,
    @SerializedName("companyLogo") val companyLogo: String,
    @SerializedName("requestTypes") val requestTypes: List<RequestTyoeResponse>
)