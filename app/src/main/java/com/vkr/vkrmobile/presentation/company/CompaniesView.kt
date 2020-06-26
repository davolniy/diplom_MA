package com.vkr.vkrmobile.presentation.company

import com.vkr.vkrmobile.model.data.net.response.company.CompanyWithBranchesResponse
import com.vkr.vkrmobile.model.data.net.response.news.NewsResponse
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface CompaniesView : MvpView {
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setData(data: List<CompanyWithBranchesResponse>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showProgress(show: Boolean)
}