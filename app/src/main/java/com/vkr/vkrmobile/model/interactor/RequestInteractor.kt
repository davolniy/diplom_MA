package com.vkr.vkrmobile.model.interactor

import com.vkr.vkrmobile.model.data.net.response.request.DynamicField
import com.vkr.vkrmobile.model.repository.RequestRepository
import javax.inject.Inject

class RequestInteractor @Inject constructor(
    private val requestRepository: RequestRepository
) {
    fun getCompanyRequestTypes() = requestRepository.getCompanyRequestTypes()

    fun getRequests() = requestRepository.getRequests()

    fun getRequestType(requestTypeId: Long) = requestRepository.getRequestType(requestTypeId)

    fun makeRequest(requestTypeId: Long, fields: List<DynamicField>) = requestRepository.makeRequest(requestTypeId, fields)
}