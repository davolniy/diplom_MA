package com.vkr.vkrmobile.presentation.company

import com.vkr.vkrmobile.model.data.net.response.catalog.CatalogResponse
import com.vkr.vkrmobile.model.interactor.*
import com.vkr.vkrmobile.model.navigation.AppRouter
import com.vkr.vkrmobile.model.system.ErrorHandler
import com.vkr.vkrmobile.model.system.SystemMessageNotifier
import com.vkr.vkrmobile.presentation.global.BasePresenter
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class CompanyPresenter @Inject constructor(
    private val router: AppRouter,
    private val companyInteractor: CompanyInteractor,
    private val catalogInteractor: CatalogInteractor,
    private val cartInteractor: CartInteractor,
    private val newsInteractor: NewsInteractor,
    private val errorHandler: ErrorHandler,
    private val systemMessageNotifier: SystemMessageNotifier,
    initParams: InitParams
) : BasePresenter<CompanyView>() {

    private var selectedCatalogCategoryId = 0L
    private var allCatalogs = listOf<CatalogResponse>()

    class InitParams(
        val companyId: Long
    )

    val companyId = initParams.companyId

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadCompany(companyId)
        loadActions(companyId)
        loadCatalogs(companyId)
    }

    fun onBackPressed() {
        router.exit()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun refresh() {
    }

    fun selectCatalogCategory(catalogId: Long) {
        if (selectedCatalogCategoryId != catalogId) {
            selectedCatalogCategoryId = catalogId
            loadCatalog(catalogId)
        }
    }

    fun loadActions(companyId: Long) = newsInteractor
        .getCompanyActions(companyId)
        .subscribe(
            {
                viewState.setCompanyActions(it)
            },
            {
                errorHandler.proceed(it) { message ->
                    systemMessageNotifier.send(message)
                }
            })

    fun addProductToCart(productId: Long) = cartInteractor
        .addToCart(companyId, productId)
        .subscribe(
            {
                systemMessageNotifier.send("Продукт успешно добавлен в корзину")
            },
            {
                errorHandler.proceed(it) { message ->
                    systemMessageNotifier.send(message)
                }
            })

    fun loadCatalogs(companyId: Long) = catalogInteractor
        .getCompanyCatalogs(companyId)
        .subscribe(
            {
                viewState.setCatalogs(it, it[0].id)
                allCatalogs = it
            },
            {
                errorHandler.proceed(it) { message ->
                    systemMessageNotifier.send(message)
                }
            })

    fun loadCatalog(catalogId: Long) = catalogInteractor
        .getCatalogWithProducts(catalogId)
        .subscribe(
            {
                viewState.setCatalogItems(it.catalogProducts)
            },
            {
                errorHandler.proceed(it) { message ->
                    systemMessageNotifier.send(message)
                }
            })

    fun loadCompany(companyId: Long) = companyInteractor
        .getCompany(companyId)
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