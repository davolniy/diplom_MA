package com.vkr.vkrmobile.presentation.chat

import com.vkr.vkrmobile.model.data.net.response.chat.ChatResponse
import com.vkr.vkrmobile.model.data.net.response.news.NewsResponse
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ChatsView : MvpView {
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setData(data: List<ChatResponse>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showProgress(show: Boolean)
}