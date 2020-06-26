package com.vkr.vkrmobile.model.repository

import com.vkr.vkrmobile.model.data.net.service.CartService
import com.vkr.vkrmobile.model.data.net.service.CatalogService
import com.vkr.vkrmobile.ui.global.fetchData
import com.vkr.vkrmobile.ui.global.fetchResult
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class CatalogRepository @Inject constructor(
    private val catalogService: CatalogService
){
    fun getCatalogWithProducts(catalogId: Long) = catalogService.getCatalogWithProducts(catalogId)
        .fetchData()
        .doOnSuccess { }
        .doOnError { }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun getCompanyCatalogs(companyId: Long) = catalogService.getCompanyCatalogs(companyId)
        .fetchData()
        .doOnSuccess { }
        .doOnError { }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}