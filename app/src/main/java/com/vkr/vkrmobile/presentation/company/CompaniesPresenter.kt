package com.vkr.vkrmobile.presentation.company

import com.vkr.vkrmobile.model.interactor.AuthInteractor
import com.vkr.vkrmobile.model.interactor.CompanyInteractor
import com.vkr.vkrmobile.model.navigation.AppRouter
import com.vkr.vkrmobile.model.system.ErrorHandler
import com.vkr.vkrmobile.model.system.SystemMessageNotifier
import com.vkr.vkrmobile.presentation.global.BasePresenter
import com.vkr.vkrmobile.presentation.global.GlobalMenuController
import com.vkr.vkrmobile.ui.screens.CompanyScreen
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class CompaniesPresenter @Inject constructor(
    private val router: AppRouter,
    private val companyInteractor: CompanyInteractor,
    private val errorHandler: ErrorHandler,
    private val systemMessageNotifier: SystemMessageNotifier,
    private val globalMenuController: GlobalMenuController
) : BasePresenter<CompaniesView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadCompaniesWithBranches()
    }

    fun onNavigationClick() {
        globalMenuController.open()
    }


    fun onBackPressed() {
        router.exit()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun refresh() {
        loadCompaniesWithBranches()
    }

    fun onCompanyClick(companyId: Long) {
        router.navigateTo(CompanyScreen(companyId))
    }

    fun loadCompaniesWithBranches() = companyInteractor
        .getCompaniesWithBranches()
        .doOnSubscribe { viewState.showProgress(true) }
        .doOnTerminate { viewState.showProgress(false) }
        .subscribe(
            {
                viewState.setData(it)
            },
            {
                errorHandler.proceed(it) { message ->
                    systemMessageNotifier.send(message)
                }
            })
}