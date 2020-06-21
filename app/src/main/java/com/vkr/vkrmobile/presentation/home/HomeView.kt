package com.vkr.vkrmobile.presentation.home

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface HomeView : MvpView {
    fun selectTab(tabId: Int)
}
