package com.vkr.vkrmobile.domain.config

import com.vkr.vkrmobile.model.data.net.response.launch.AppConfigurationParams
import javax.inject.Inject

class GlobalConfig @Inject constructor(
) {
    var apiUrl: String = ""
    var configurationParams: AppConfigurationParams = AppConfigurationParams()
}