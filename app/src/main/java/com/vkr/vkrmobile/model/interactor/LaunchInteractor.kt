package com.vkr.vkrmobile.model.interactor

import com.vkr.vkrmobile.di.AppScopes
import com.vkr.vkrmobile.domain.config.GlobalConfig
import com.vkr.vkrmobile.model.data.net.response.launch.AppConfigurationResponse
import com.vkr.vkrmobile.model.navigation.AppRouter
import com.vkr.vkrmobile.model.navigation.RequestCodes
import com.vkr.vkrmobile.model.repository.LaunchRepository
import com.vkr.vkrmobile.ui.screens.AuthScreen
import com.vkr.vkrmobile.ui.screens.HomeScreen
import com.vkr.vkrmobile.ui.screens.NewsScreen
import io.reactivex.rxjava3.core.Single
import toothpick.Toothpick
import javax.inject.Inject

class LaunchInteractor @Inject constructor(
    private val launchRepository: LaunchRepository,
    private val globalConfig: GlobalConfig,
    private val router: AppRouter
) {

    fun initialize(): Single<AppConfigurationResponse> =
        launchRepository.initialize()

    fun routeToFirstAvailableScreen() {
        val authInteractor = Toothpick.openScope(AppScopes.APP_SCOPE).getInstance(AuthInteractor::class.java)
        val params = globalConfig.configurationParams

        if (params.authRequired && !authInteractor.isSignedIn) {
            router.newRootScreen(AuthScreen())
        } else {
            if (params.menuViewMode != "Bottom") {
                router.newRootScreen(NewsScreen())
            } else {
                router.newRootScreen(HomeScreen())
            }
            router.sendResult(RequestCodes.INIT_MENU, true)
        }
    }
}