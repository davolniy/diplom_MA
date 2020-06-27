package com.vkr.vkrmobile.presentation.cart

import com.vkr.vkrmobile.model.data.net.response.cart.CartResponse
import com.vkr.vkrmobile.model.data.net.response.company.CompanyResponse
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CartsView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showProgress(show: Boolean)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setData(data: List<CartResponse>)
}