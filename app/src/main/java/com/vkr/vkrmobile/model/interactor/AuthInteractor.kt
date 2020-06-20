package com.vkr.vkrmobile.model.interactor

import com.vkr.vkrmobile.domain.config.GlobalConfig
import com.vkr.vkrmobile.model.repository.auth.AuthRepository
import com.vkr.vkrmobile.ui.screens.AuthScreen
import io.reactivex.rxjava3.core.Observable
import ru.feedback.app.model.data.auth.AuthState
import ru.feedback.app.model.data.auth.Logout
import ru.feedback.app.model.data.auth.SignedIn
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class AuthInteractor @Inject constructor(
    private val authRepository: AuthRepository,
    private val globalConfig: GlobalConfig,
    private val router: Router
) {
    val isSignedIn get() = authRepository.isSignedIn

    val isAuthRequired get() = globalConfig.configurationParams.authRequired

    fun invokeWithAuthCheck(action: () -> Void) {
        if (isSignedIn) {
            action.invoke()
        } else {
            router.navigateTo(AuthScreen())
        }
    }

    fun registration(phoneNumber: String, password: String) = authRepository.registration(phoneNumber, password)

    fun authorize(phoneNumber: String, password: String) = authRepository.authorize(phoneNumber, password)

    val authState: Observable<AuthState> = authRepository.authChangesObservable.map { isSignedIn ->
        when {
            isSignedIn -> SignedIn()
            else -> Logout(isAuthRequired)
        }
    }
}