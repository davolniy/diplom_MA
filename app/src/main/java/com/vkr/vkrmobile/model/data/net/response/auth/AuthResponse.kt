package com.vkr.vkrmobile.model.data.net.response.auth

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("userId") val userId: Long?,
    @SerializedName("token") val token: String?
)