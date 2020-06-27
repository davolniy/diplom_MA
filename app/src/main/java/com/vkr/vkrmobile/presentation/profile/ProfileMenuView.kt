package com.vkr.vkrmobile.presentation.profile

import com.vkr.vkrmobile.domain.menu.CustomMenuItem
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ProfileMenuView : MvpView {
    fun setMenu(items: List<CustomMenuItem>)
}