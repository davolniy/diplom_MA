package com.vkr.vkrmobile.presentation.service

import com.vkr.vkrmobile.model.data.net.response.employee.EmployeeWithTimeCellsResponse
import com.vkr.vkrmobile.model.data.net.response.service.ServiceResponse
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ServicesView : MvpView {
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showProgress(show: Boolean)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setData(data: List<ServiceResponse>)
}