package com.vkr.vkrmobile.model.repository

import com.vkr.vkrmobile.model.data.net.service.ReviewService
import com.vkr.vkrmobile.ui.global.fetchData
import com.vkr.vkrmobile.ui.global.fetchResult
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ReviewRepository @Inject constructor(
    private val reviewService: ReviewService
) {
    fun getCompanyReviews(companyId: Long) = reviewService.getCompanyReviews(companyId)
        .fetchData()
        .doOnSuccess { }
        .doOnError { }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun getReviews() = reviewService.getReviews()
        .fetchData()
        .doOnSuccess { }
        .doOnError { }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun makeReview(companyId: Long, text: String, value: Long) = reviewService.makeReview(companyId, text, value)
        .fetchResult()
        .doOnComplete {  }
        .doOnError { }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}