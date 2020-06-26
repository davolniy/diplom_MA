package com.vkr.vkrmobile.model.repository

import com.vkr.vkrmobile.model.data.net.service.CartService
import com.vkr.vkrmobile.ui.global.fetchData
import com.vkr.vkrmobile.ui.global.fetchResult
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class CartRepository @Inject constructor(
    private val cartService: CartService
){
    fun getAllCarts() = cartService.getAllCarts()
        .fetchData()
        .doOnSuccess { }
        .doOnError { }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun addToCart(companyId: Long, productId: Long) = cartService.addToCart(companyId, productId)
        .fetchResult()
        .doOnComplete {  }
        .doOnError { }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun updateProductCount(companyId: Long, productId: Long, count: Int) = cartService.updateProductCount(companyId, productId, count)
        .fetchResult()
        .doOnComplete { }
        .doOnError { }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}