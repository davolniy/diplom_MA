package com.vkr.vkrmobile.di.provider

import com.vkr.vkrmobile.domain.config.GlobalConfig
import javax.inject.Inject
import javax.inject.Provider

class ApiPathProvider @Inject constructor(
    private val globalConfig: GlobalConfig
) : Provider<String> {

    override fun get(): String = "${globalConfig.apiUrl}"
}
