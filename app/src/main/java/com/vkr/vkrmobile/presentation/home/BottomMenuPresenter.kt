package com.vkr.vkrmobile.presentation.home

import com.vkr.vkrmobile.model.interactor.AuthInteractor
import com.vkr.vkrmobile.model.navigation.AppRouter
import com.vkr.vkrmobile.model.system.ErrorHandler
import com.vkr.vkrmobile.model.system.SystemMessageNotifier
import com.vkr.vkrmobile.presentation.global.BasePresenter
import moxy.InjectViewState
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@InjectViewState
class BottomMenuPresenter @Inject constructor(
    private val router: AppRouter,
    private val authInteractor: AuthInteractor,
    private val systemMessageNotifier: SystemMessageNotifier,
    private val errorHandler: ErrorHandler
) : BasePresenter<BottomMenuView>() {

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
        authInteractor.invokeWithAuthCheck(screen) {
            router.navigateTo(screen)
        }
    }
}
