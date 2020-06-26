package com.vkr.vkrmobile.model.data.net.service

import com.vkr.vkrmobile.model.data.ApiMethods
import com.vkr.vkrmobile.model.data.net.ApiResponse
import com.vkr.vkrmobile.model.data.net.ApiResponseEmpty
import com.vkr.vkrmobile.model.data.net.response.review.UserReviewResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ReviewService {
    @GET(ApiMethods.Reviews.GetCompanyReviews)
    fun getCompanyReviews(
        @Query("companyId") companyId: Long
    ) : Single<ApiResponse<UserReviewResponse>>

    @GET(ApiMethods.Reviews.GetReviews)
    fun getReviews() : Single<ApiResponse<UserReviewResponse>>

    @POST(ApiMethods.Reviews.MakeReview)
    fun makeReview(
        @Query("companyId") companyId: Long,
        @Query("text") text: String,
        @Query("value") value: Long
    ) : Single<ApiResponseEmpty>
}