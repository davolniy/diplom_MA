package com.vkr.vkrmobile.presentation.profile

import com.vkr.vkrmobile.model.interactor.AuthInteractor
import com.vkr.vkrmobile.model.navigation.AppRouter
import com.vkr.vkrmobile.presentation.global.BasePresenter
import com.vkr.vkrmobile.presentation.global.GlobalMenuController
import moxy.InjectViewState
import ru.terrakok.cicerone.android.support.SupportAppScreen
import javax.inject.Inject

@InjectViewState
class ProfileMenuPresenter @Inject constructor(
    private val router: AppRouter,
    private val authInteractor: AuthInteractor,
    private val globalMenuController: GlobalMenuController
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

    fun onEditProfileButtonClick() {
    }

    fun onMenuRowClick(screen: SupportAppScreen) {
        authInteractor.invokeWithAuthCheck(screen) {
            globalMenuController.close()
            router.navigateTo(screen)
        }
    }

    fun logout() {
        authInteractor.logout()
    }
}