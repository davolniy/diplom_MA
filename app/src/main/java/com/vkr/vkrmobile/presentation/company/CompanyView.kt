package com.vkr.vkrmobile.presentation.company

import com.vkr.vkrmobile.model.data.net.response.cart.CartItemResponse
import com.vkr.vkrmobile.model.data.net.response.catalog.CatalogResponse
import com.vkr.vkrmobile.model.data.net.response.company.CompanyResponse
import com.vkr.vkrmobile.model.data.net.response.company.CompanyWithBranchesResponse
import com.vkr.vkrmobile.model.data.net.response.news.NewsResponse
import com.vkr.vkrmobile.model.data.net.response.product.ProductResponse
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CompanyView : MvpView {
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setData(data: CompanyResponse)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setCatalogs(catalogs: List<CatalogResponse>, selectedCategoryId: Long)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setCatalogItems(items: List<ProductResponse>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setCompanyActions(items: List<NewsResponse>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun pickDate(productId: Long)
}