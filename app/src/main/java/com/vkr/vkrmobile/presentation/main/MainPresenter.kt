package com.vkr.vkrmobile.presentation.main

import com.vkr.vkrmobile.presentation.global.BasePresenter
import com.vkr.vkrmobile.ui.screens.LaunchScreen
import moxy.InjectViewState
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor(
    private val router: Router
) : BasePresenter<MainView>() {

    override fun attachView(view: MainView?) {
        super.attachView(view)
    }

    fun initialize() {
        router.newRootScreen(LaunchScreen())
    }

    fun onBackPressed() = router.finishChain()
}