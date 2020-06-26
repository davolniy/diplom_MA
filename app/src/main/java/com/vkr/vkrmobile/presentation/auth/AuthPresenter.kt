package com.vkr.vkrmobile.presentation.auth

import com.vkr.vkrmobile.model.interactor.AuthInteractor
import com.vkr.vkrmobile.model.interactor.LaunchInteractor
import com.vkr.vkrmobile.model.navigation.AppRouter
import com.vkr.vkrmobile.model.system.ErrorHandler
import com.vkr.vkrmobile.model.system.SystemMessageNotifier
import com.vkr.vkrmobile.presentation.global.BasePresenter
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class AuthPresenter @Inject constructor(
    private val launchInteractor: LaunchInteractor,
    private val authInteractor: AuthInteractor,
    private val router: AppRouter,
    private val errorHandler: ErrorHandler,
    private val systemMessageNotifier: SystemMessageNotifier
) : BasePresenter<AuthView>() {
    fun onBackPressed() {
        router.exit()
    }

    fun registration(phoneNumber: String, password: String) =
        authInteractor.registration(phoneNumber, password)
            .subscribe({
                launchInteractor.routeToFirstAvailableScreen()
            }, { throwable ->
                errorHandler.proceed(throwable) {
                    systemMessageNotifier.send(it)
                }
            })
            .untilDestroy()


    fun authorization(phoneNumber: String, password: String) =
        authInteractor.authorize(phoneNumber, password)
            .subscribe({
                launchInteractor.routeToFirstAvailableScreen()
            }, { throwable ->
                errorHandler.proceed(throwable) {
                    systemMessageNotifier.send(it)
                }
            })
            .untilDestroy()

}