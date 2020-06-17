package com.vkr.vkrmobile.model.data.net.response.launch

import com.google.gson.annotations.SerializedName

class AppConfigurationInfo (
    @SerializedName("ApiUrl") val apiUrl: String,
    @SerializedName("AppConfigurationParams") val appConfigurationParams: AppConfigurationParams
)
