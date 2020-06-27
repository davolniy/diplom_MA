package com.vkr.vkrmobile.model.interactor

import com.vkr.vkrmobile.domain.config.GlobalConfig
import com.vkr.vkrmobile.model.data.auth.AuthState
import com.vkr.vkrmobile.model.data.auth.Logout
import com.vkr.vkrmobile.model.data.auth.SignedIn
import com.vkr.vkrmobile.model.navigation.AppRouter
import com.vkr.vkrmobile.model.navigation.RequestCodes
import com.vkr.vkrmobile.model.repository.AuthRepository
import com.vkr.vkrmobile.ui.screens.AuthScreen
import io.reactivex.rxjava3.core.Observable
import ru.terrakok.cicerone.android.support.SupportAppScreen
import javax.inject.Inject

class AuthInteractor @Inject constructor(
    private val authRepository: AuthRepository,
    private val globalConfig: GlobalConfig,
    private val router: AppRouter
) {
    val isSignedIn get() = authRepository.isSignedIn

    val isAuthRequired get() = globalConfig.configurationParams.authRequired

    fun invokeWithAuthCheck(screen: SupportAppScreen, action: () -> Unit) {
        if (isSignedIn) {
            action.invoke()
        } else {
            router.navigateTo(AuthScreen())
        }
    }

    fun invokeWithAuthCheck(action: () -> Unit) {
        if (isSignedIn) {
            action.invoke()
        } else {
            router.navigateTo(AuthScreen())
        }
    }

    fun registration(phoneNumber: String, password: String) = authRepository.registration(phoneNumber, password)

    fun authorize(phoneNumber: String, password: String) = authRepository.authorize(phoneNumber, password)

    fun editProfile(name: String, email: String, gender: Boolean?) = authRepository.editProfile(name, email, gender)

    fun getProfile() = authRepository.getProfile()

    fun logout() {
        authRepository.logout()

        if (authRepository.isSignedIn) {
            authRepository.clearAuthData()
        }

        if (isAuthRequired) {
            router.sendResult(RequestCodes.INIT_MENU, false)
            router.newRootScreen(AuthScreen())
        }
    }
}