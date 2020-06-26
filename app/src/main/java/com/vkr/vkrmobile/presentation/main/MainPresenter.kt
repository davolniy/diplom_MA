package com.vkr.vkrmobile.presentation.main

import com.vkr.vkrmobile.di.AppScopes
import com.vkr.vkrmobile.model.navigation.AppRouter
import com.vkr.vkrmobile.model.navigation.RequestCodes
import com.vkr.vkrmobile.presentation.global.BasePresenter
import com.vkr.vkrmobile.ui.screens.LaunchScreen
import moxy.InjectViewState
import ru.terrakok.cicerone.Router
import toothpick.Toothpick
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor(
    private val router: AppRouter
) : BasePresenter<MainView>() {

    override fun attachView(view: MainView?) {
        super.attachView(view)
    }

    fun initialize() {
        router.setResultListener(RequestCodes.INIT) {
            Toothpick.openScopes(AppScopes.APP_SCOPE, AppScopes.MAIN_ACTIVITY_SCOPE)
            viewState.initMenu()
            router.removeResultListener(RequestCodes.INIT)
        }
        router.newRootScreen(LaunchScreen())
    }

    fun onBackPressed() = router.finishChain()
}