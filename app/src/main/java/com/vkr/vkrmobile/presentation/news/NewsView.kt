package com.vkr.vkrmobile.presentation.news

import com.vkr.vkrmobile.model.data.net.response.news.NewsResponse
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface NewsView : MvpView {
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setData(data: List<NewsResponse>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun clearData()
}