package com.vkr.vkrmobile.model.repository

import com.vkr.vkrmobile.model.data.net.response.request.DynamicField
import com.vkr.vkrmobile.model.data.net.service.RequestService
import com.vkr.vkrmobile.ui.global.fetchData
import com.vkr.vkrmobile.ui.global.fetchResult
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RequestRepository @Inject constructor(
    private val requestService: RequestService
) {
    fun getCompanyRequestTypes() = requestService.getCompanyRequestTypes()
        .fetchData()
        .doOnSuccess { }
        .doOnError { }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun getRequests() = requestService.getRequests()
        .fetchData()
        .doOnSuccess { }
        .doOnError { }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun getRequestType(requestTypeId: Long) = requestService.getRequestType(requestTypeId)
        .fetchData()
        .doOnSuccess { }
        .doOnError { }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun makeRequest(requestTypeId: Long, fields: List<DynamicField>) = requestService.makeRequest(requestTypeId, fields)
        .fetchResult()
        .doOnComplete {  }
        .doOnError { }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}