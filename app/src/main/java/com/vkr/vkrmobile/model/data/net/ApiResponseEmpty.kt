package com.vkr.vkrmobile.model.data.net

import com.google.gson.annotations.SerializedName

data class ApiResponseEmpty constructor(
    @SerializedName("success") val isSuccess: Boolean?,
    @SerializedName("errorMessage") val errorMessage: String?,
    @SerializedName("errorCode") val errorCode: Int?
)
