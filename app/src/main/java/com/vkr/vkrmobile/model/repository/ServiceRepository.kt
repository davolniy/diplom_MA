package com.vkr.vkrmobile.model.repository

import com.vkr.vkrmobile.model.data.net.service.ServiceService
import com.vkr.vkrmobile.ui.global.fetchData
import com.vkr.vkrmobile.ui.global.fetchResult
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class ServiceRepository @Inject constructor(
    private val serviceService: ServiceService
){
    fun getEmployees(productId: Long, selectedDate: Date) = serviceService.getEmployees(productId, selectedDate)
        .fetchData()
        .doOnSuccess { }
        .doOnError { }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun getServices() = serviceService.getServices()
        .fetchData()
        .doOnSuccess { }
        .doOnError { }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun makeService(companyId: Long, productId: Long, employeeId: Long, serviceDate: Date) = serviceService.makeService(companyId, productId, employeeId, serviceDate)
        .fetchResult()
        .doOnComplete {  }
        .doOnError { }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}