package com.vkr.vkrmobile.presentation.company

import com.vkr.vkrmobile.model.interactor.AuthInteractor
import com.vkr.vkrmobile.model.interactor.CompanyInteractor
import com.vkr.vkrmobile.model.navigation.AppRouter
import com.vkr.vkrmobile.model.system.ErrorHandler
import com.vkr.vkrmobile.model.system.SystemMessageNotifier
import com.vkr.vkrmobile.presentation.global.BasePresenter
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class CompaniesPresenter @Inject constructor(
    private val router: AppRouter,
    private val companyInteractor: CompanyInteractor,
    private val errorHandler: ErrorHandler,
    private val systemMessageNotifier: SystemMessageNotifier
) : BasePresenter<CompaniesView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadCompaniesWithBranches()
    }

    fun onBackPressed() {
        router.exit()
    }

    override fun onDestroy() {
        super.onDestroy()
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