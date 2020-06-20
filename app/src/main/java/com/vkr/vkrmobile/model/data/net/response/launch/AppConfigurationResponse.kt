package com.vkr.vkrmobile.model.data.net.response.launch

import com.google.gson.annotations.SerializedName

    class AppConfigurationResponse (
    @SerializedName("apiUrl") val apiUrl: String,
    @SerializedName("appConfigurationParams") val appConfigurationParams: AppConfigurationParams
)
