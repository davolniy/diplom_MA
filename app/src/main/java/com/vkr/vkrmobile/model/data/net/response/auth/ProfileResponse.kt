package com.vkr.vkrmobile.model.data.net.response.auth

import com.google.gson.annotations.SerializedName

class ProfileResponse (
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("gender") val gender: Boolean?
)