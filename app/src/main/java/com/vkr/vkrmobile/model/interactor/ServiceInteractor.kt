package com.vkr.vkrmobile.model.interactor

import com.vkr.vkrmobile.model.repository.ServiceRepository
import java.util.*
import javax.inject.Inject

class ServiceInteractor @Inject constructor(
    private val serviceRepository: ServiceRepository
){
    fun getRequests(productId: Long, selectedDate: Date) = serviceRepository.getEmployees(productId, selectedDate)

    fun getRequestType() = serviceRepository.getServices()

    fun makeRequest(companyId: Long, productId: Long, employeeId: Long, serviceDate: Date) = serviceRepository.makeService(companyId, productId, employeeId, serviceDate)
}