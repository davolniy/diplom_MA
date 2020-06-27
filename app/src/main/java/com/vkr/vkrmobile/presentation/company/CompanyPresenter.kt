package com.vkr.vkrmobile.presentation.company

import com.vkr.vkrmobile.model.data.net.response.catalog.CatalogResponse
import com.vkr.vkrmobile.model.interactor.*
import com.vkr.vkrmobile.model.navigation.AppRouter
import com.vkr.vkrmobile.model.navigation.RequestCodes
import com.vkr.vkrmobile.model.system.ErrorHandler
import com.vkr.vkrmobile.model.system.SystemMessageNotifier
import com.vkr.vkrmobile.presentation.global.BasePresenter
import com.vkr.vkrmobile.presentation.global.GlobalMenuController
import com.vkr.vkrmobile.ui.screens.EmployeeSelectionScreen
import moxy.InjectViewState
import java.util.*
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
    private val globalMenuController: GlobalMenuController,
    initParams: InitParams
) : BasePresenter<CompanyView>() {

    private var selectedCatalogCategoryId = 0L
    private var allCatalogs = listOf<CatalogResponse>()

    class InitParams(
        val companyId: Long
    )

    val companyId = initParams.companyId

    fun onNavigationClick() {
        globalMenuController.open()
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        refresh()
    }

    override fun attachView(view: CompanyView?) {
        super.attachView(view)
        refresh()
    }

    fun onBackPressed() {
        router.exit()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun refresh() {
        loadCompany(companyId)
        loadActions(companyId)
        loadCatalogs(companyId)
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

    fun getEmployees(productId: Long, selectedDate: Long) {
        router.setResultListener(RequestCodes.SERVICE_MADE) {
            refresh()
        }
        router.navigateTo(EmployeeSelectionScreen(companyId, productId, selectedDate))
    }

    fun addProductToCart(productId: Long, duration: Long?) {
        if (duration != null) {
            viewState.pickDate(productId)
        } else {
            cartInteractor
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
        }
    }

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