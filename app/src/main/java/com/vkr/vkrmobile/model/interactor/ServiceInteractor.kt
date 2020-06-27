package com.vkr.vkrmobile.model.interactor

import com.vkr.vkrmobile.model.repository.ServiceRepository
import java.util.*
import javax.inject.Inject

class ServiceInteractor @Inject constructor(
    private val serviceRepository: ServiceRepository
){
    fun getEmployees(productId: Long, selectedDate: String) = serviceRepository.getEmployees(productId, selectedDate)

    fun getServices() = serviceRepository.getServices()

    fun makeService(companyId: Long, productId: Long, employeeId: Long, serviceDate: String) = serviceRepository.makeService(companyId, productId, employeeId, serviceDate)
}