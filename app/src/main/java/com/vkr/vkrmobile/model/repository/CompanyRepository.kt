package com.vkr.vkrmobile.model.repository

import com.vkr.vkrmobile.model.data.net.service.CompanyService
import com.vkr.vkrmobile.ui.global.fetchData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class CompanyRepository @Inject constructor(
    private val companyService: CompanyService
) {
    fun getBranchCompanies() = companyService.getBranchCompanies()
        .fetchData()
        .doOnSuccess { }
        .doOnError { }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun getParentCompanies() = companyService.getParentCompanies()
        .fetchData()
        .doOnSuccess { }
        .doOnError { }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun getCompaniesWithBranches() = companyService.getCompaniesWithBranches()
        .fetchData()
        .doOnSuccess { }
        .doOnError { }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun getCompany(companyId: Long) = companyService.getCompany(companyId)
        .fetchData()
        .doOnSuccess { }
        .doOnError { }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}