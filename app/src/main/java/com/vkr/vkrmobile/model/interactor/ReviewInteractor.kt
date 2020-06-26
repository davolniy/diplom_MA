package com.vkr.vkrmobile.model.interactor

import com.vkr.vkrmobile.model.repository.ReviewRepository
import javax.inject.Inject

class ReviewInteractor @Inject constructor(
    private val reviewRepository: ReviewRepository
) {
    fun getCompanyReviews(companyId: Long) = reviewRepository.getCompanyReviews(companyId)

    fun getReviews() = reviewRepository.getReviews()

    fun makeRequest(companyId: Long, text: String, value: Long) = reviewRepository.makeReview(companyId, text, value)
}