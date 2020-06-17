package com.vkr.vkrmobile.model.interactor

import com.vkr.vkrmobile.model.data.net.response.launch.AppConfigurationInfo
import com.vkr.vkrmobile.model.repository.launch.LaunchRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LaunchInteractor @Inject constructor(
    private val launchRepository: LaunchRepository
) {
    fun initialize(): Single<AppConfigurationInfo> =
        launchRepository.initialize()
}