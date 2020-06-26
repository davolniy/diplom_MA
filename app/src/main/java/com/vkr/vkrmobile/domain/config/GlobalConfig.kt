package com.vkr.vkrmobile.domain.config

import android.graphics.Color
import com.vkr.vkrmobile.model.data.net.response.launch.AppConfigurationParams
import javax.inject.Inject

class GlobalConfig @Inject constructor(
) {
    var apiUrl: String = ""
    var configurationParams: AppConfigurationParams = AppConfigurationParams()

    val accentColor get() = Color.parseColor(configurationParams.accentColor)

    val isDrawerEnabled get() = configurationParams.menuViewMode == "Top"

    val isFABEnabled get() = configurationParams.menuViewMode == "FAB"
}