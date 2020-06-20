package com.vkr.vkrmobile.ui.global

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.feedback.app.model.data.net.ApiError
import ru.feedback.app.model.data.net.ApiResponse
import ru.feedback.app.model.data.net.ApiResponseEmpty

fun <T> Single<ApiResponse<T>>.fetchData(): Single<T> = flatMap {
    when {
        it.isSuccess == true && it.data != null -> Single.just(it.data)
        it.errorCode != null -> Single.error(ApiError(it.errorCode, it.errorMessage))
        else -> Single.error(Throwable(it.errorMessage))
    }
}

fun Single<ApiResponseEmpty>.fetchResult(): Completable = flatMapCompletable {
    when {
        it.isSuccess == true -> Completable.complete()
        it.errorCode != null -> Completable.error(ApiError(it.errorCode, it.errorMessage))
        else -> Completable.error(Throwable(it.errorMessage))
    }
}
