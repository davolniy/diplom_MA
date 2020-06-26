package com.vkr.vkrmobile.model.repository

import com.vkr.vkrmobile.model.data.net.service.OrderService
import com.vkr.vkrmobile.ui.global.fetchData
import com.vkr.vkrmobile.ui.global.fetchResult
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class OrderRepository @Inject constructor(
    private val orderService: OrderService
) {
    fun getOrders() = orderService.getOrders()
        .fetchData()
        .doOnSuccess { }
        .doOnError { }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun makeOrder(cartId: Long) = orderService.makeOrder(cartId)
        .fetchResult()
        .doOnComplete { }
        .doOnError { }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}