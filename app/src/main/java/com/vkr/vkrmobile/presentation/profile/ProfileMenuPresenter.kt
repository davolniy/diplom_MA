package com.vkr.vkrmobile.presentation.profile

import com.vkr.vkrmobile.model.interactor.auth.AuthInteractor
import com.vkr.vkrmobile.model.navigation.AppRouter
import com.vkr.vkrmobile.presentation.global.BasePresenter
import com.vkr.vkrmobile.presentation.home.HomeView
import moxy.InjectViewState
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen
import javax.inject.Inject

@InjectViewState
class ProfileMenuPresenter @Inject constructor(
    private val router: AppRouter,
    private val authInteractor: AuthInteractor
) : BasePresenter<ProfileMenuView>()  {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun onBackPressed() {
        router.exit()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun onMenuRowClick(screen: SupportAppScreen) {
        router.navigateTo(screen)
    }

    fun logout() {
        authInteractor.logout()
    }
}