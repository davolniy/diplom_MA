package com.vkr.vkrmobile.presentation.company

import com.vkr.vkrmobile.model.interactor.auth.AuthInteractor
import com.vkr.vkrmobile.model.navigation.AppRouter
import com.vkr.vkrmobile.model.system.ErrorHandler
import com.vkr.vkrmobile.model.system.SystemMessageNotifier
import com.vkr.vkrmobile.presentation.global.BasePresenter
import moxy.InjectViewState
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class CompaniesPresenter @Inject constructor(
    private val router: AppRouter,
    private val authInteractor: AuthInteractor,
    private val errorHandler: ErrorHandler,
    private val systemMessageNotifier: SystemMessageNotifier
) : BasePresenter<CompaniesView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun onBackPressed() {
        router.exit()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}