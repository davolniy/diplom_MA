package com.vkr.vkrmobile.presentation.cart

import com.vkr.vkrmobile.model.interactor.CartInteractor
import com.vkr.vkrmobile.model.interactor.OrderInteractor
import com.vkr.vkrmobile.model.navigation.AppRouter
import com.vkr.vkrmobile.model.system.ErrorHandler
import com.vkr.vkrmobile.model.system.SystemMessageNotifier
import com.vkr.vkrmobile.presentation.global.BasePresenter
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class CartsPresenter @Inject constructor(
    private val router: AppRouter,
    private val cartInteractor: CartInteractor,
    private val orderInteractor: OrderInteractor,
    private val errorHandler: ErrorHandler,
    private val systemMessageNotifier: SystemMessageNotifier
) : BasePresenter<CartsView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        refresh()
    }

    fun onBackPressed() {
        router.exit()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun refresh() {
        loadCarts()
    }

    fun onCountClick(companyId: Long, productId: Long, count: Int) = cartInteractor
        .updateProductCount(companyId, productId, count)
        .doOnSubscribe { viewState.showProgress(true) }
        .doOnTerminate { viewState.showProgress(false) }
        .subscribe(
            {
                refresh()
            },
            {
                errorHandler.proceed(it) { message ->
                    systemMessageNotifier.send(message)
                }
            })

    fun onOrderClick(cartId: Long) = orderInteractor
        .makeOrder(cartId)
        .doOnSubscribe { viewState.showProgress(true) }
        .doOnTerminate { viewState.showProgress(false) }
        .subscribe(
            {
                systemMessageNotifier.send("Заказ оформлен успешно")
                refresh()
            },
            {
                errorHandler.proceed(it) { message ->
                    systemMessageNotifier.send(message)
                }
            })

    fun loadCarts() = cartInteractor
        .getAllCarts()
        .doOnSubscribe { viewState.showProgress(true) }
        .doOnTerminate { viewState.showProgress(false) }
        .subscribe(
            {
                viewState.setData(it.filter { cart -> cart.cartItems.isNotEmpty() })
            },
            {
                errorHandler.proceed(it) { message ->
                    systemMessageNotifier.send(message)
                }
            })
}