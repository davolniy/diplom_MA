package com.vkr.vkrmobile.presentation.service

import com.vkr.vkrmobile.model.data.net.ServerDateConverter
import com.vkr.vkrmobile.model.interactor.ServiceInteractor
import com.vkr.vkrmobile.model.navigation.AppRouter
import com.vkr.vkrmobile.model.navigation.RequestCodes
import com.vkr.vkrmobile.model.system.ErrorHandler
import com.vkr.vkrmobile.model.system.SystemMessageNotifier
import com.vkr.vkrmobile.presentation.global.BasePresenter
import com.vkr.vkrmobile.ui.screens.CompanyScreen
import moxy.InjectViewState
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@InjectViewState
class EmployeeSelectionPresenter @Inject constructor(
    private val router: AppRouter,
    private val serviceInteractor: ServiceInteractor,
    private val errorHandler: ErrorHandler,
    private val systemMessageNotifier: SystemMessageNotifier,
    initParams: InitParams
) : BasePresenter<EmployeeSelectionView>() {

    class InitParams(
        val companyId: Long,
        val productId: Long,
        val selectedDate: Long
    )

    val companyId = initParams.companyId
    val productId = initParams.productId
    val selectedDate = initParams.selectedDate

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH-mm", Locale.getDefault())
        val date = format.parse(format.format(selectedDate))
        date?.run {
            getEmployees(productId, this)
        }
    }

    fun onBackPressed() {
        router.backTo(CompanyScreen(companyId))
    }

    fun onDateClick(employeeId: Long, selectedCellDate: String) {
        serviceInteractor
            .makeService(companyId, productId, employeeId, selectedCellDate)
            .doOnSubscribe { viewState.showProgress(true) }
            .doOnTerminate { viewState.showProgress(false) }
            .subscribe(
                {
                    systemMessageNotifier.send("Вы успешно записались")
                    router.sendResult(RequestCodes.SERVICE_MADE, true)
                    onBackPressed()
                },
                {
                    errorHandler.proceed(it) { message ->
                        systemMessageNotifier.send(message)
                    }
                })
    }

    fun getEmployees(productId: Long, selectedDate: Date) = serviceInteractor
        .getEmployees(productId, ServerDateConverter.convertToServerDate(selectedDate))
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