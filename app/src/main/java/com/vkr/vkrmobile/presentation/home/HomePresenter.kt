package com.vkr.vkrmobile.presentation.home

import com.vkr.vkrmobile.model.navigation.AppRouter
import com.vkr.vkrmobile.model.system.ErrorHandler
import com.vkr.vkrmobile.model.system.SystemMessageNotifier
import com.vkr.vkrmobile.presentation.global.BasePresenter
import com.vkr.vkrmobile.ui.screens.BottomMenuScreen
import moxy.InjectViewState
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.terrakok.cicerone.Router
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@InjectViewState
class HomePresenter @Inject constructor(
    private val router: AppRouter,
    private val systemMessageNotifier: SystemMessageNotifier,
    private val errorHandler: ErrorHandler
) : BasePresenter<HomeView>() {

    companion object {
        private const val DOUBLE_BACK_PRESSED_DELAY = 2000L
    }

    private var backPressedOnce = false
    private var doubleBackPressedDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun onBackPressed() {
        if (backPressedOnce) {
            router.exit()
            return
        }

        backPressedOnce = true
        systemMessageNotifier.send("Нажмите еще раз для выхода")

        doubleBackPressedDisposable.add(Completable.timer(DOUBLE_BACK_PRESSED_DELAY, TimeUnit.MILLISECONDS)
            .doOnComplete { backPressedOnce = false }
            .subscribe({ }, { }))
    }

    override fun onDestroy() {
        super.onDestroy()
        doubleBackPressedDisposable.dispose()
    }
}
