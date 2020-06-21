package com.vkr.vkrmobile.model.interactor.launch

import com.vkr.vkrmobile.domain.config.GlobalConfig
import com.vkr.vkrmobile.model.data.net.response.launch.AppConfigurationResponse
import com.vkr.vkrmobile.model.navigation.AppRouter
import com.vkr.vkrmobile.model.repository.launch.LaunchRepository
import com.vkr.vkrmobile.ui.screens.AuthScreen
import com.vkr.vkrmobile.ui.screens.CompaniesScreen
import com.vkr.vkrmobile.ui.screens.HomeScreen
import io.reactivex.rxjava3.core.Single
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class LaunchInteractor @Inject constructor(
    private val launchRepository: LaunchRepository,
    private val globalConfig: GlobalConfig,
    private val router: AppRouter
) {

    fun initialize(): Single<AppConfigurationResponse> =
        launchRepository.initialize()

    fun routeToFirstAvailableScreen(isSignIn: Boolean = false) {
        val params = globalConfig.configurationParams

        if (params.authRequired && !isSignIn) {
            router.newRootScreen(AuthScreen())
        } else {
            if (params.menuViewMode != "Bottom") {
                router.newRootScreen(CompaniesScreen())
            } else {
                router.newRootScreen(HomeScreen())
            }
        }
    }
}