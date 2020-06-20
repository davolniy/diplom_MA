package com.vkr.vkrmobile.presentation.launch

import com.vkr.vkrmobile.di.AppScopes
import com.vkr.vkrmobile.di.module.AppModule
import com.vkr.vkrmobile.model.interactor.LaunchInteractor
import com.vkr.vkrmobile.model.system.ErrorHandler
import com.vkr.vkrmobile.model.system.SystemMessageNotifier
import com.vkr.vkrmobile.presentation.global.BasePresenter
import com.vkr.vkrmobile.ui.screens.AuthScreen
import com.vkr.vkrmobile.ui.screens.HomeScreen
import com.vkr.vkrmobile.ui.screens.NewsScreen
import moxy.InjectViewState
import ru.terrakok.cicerone.Router
import toothpick.Toothpick
import javax.inject.Inject

@InjectViewState
class LaunchPresenter @Inject constructor(
    private val launchInteractor: LaunchInteractor,
    private val router: Router,
    private val errorHandler: ErrorHandler,
    private val systemMessageNotifier: SystemMessageNotifier
) : BasePresenter<LaunchView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        initialize()
    }

    fun onBackPressed() {
        router.finishChain()
    }

    private fun initialize() {
        launchInteractor.initialize()
            .subscribe({
                Toothpick
                    .openScopes(AppScopes.LAUNCH_SCOPE, AppScopes.APP_SCOPE)
                    .installModules(AppModule())

                Toothpick
                    .openScopes(AppScopes.APP_SCOPE, AppScopes.MAIN_ACTIVITY_SCOPE)

                val params = it.appConfigurationParams

                if (params.authRequired) {
                    router.newRootScreen(AuthScreen())
                } else {
                    launchInteractor.routeToFirstAvailableScreen()
                }

            }, { throwable ->
                errorHandler.proceed(throwable) {
                    systemMessageNotifier.send(it)
                }
            })
            .untilDestroy()
    }
}
