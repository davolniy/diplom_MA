package com.vkr.vkrmobile.model.interactor

import com.vkr.vkrmobile.model.repository.CatalogRepository
import javax.inject.Inject

class CatalogInteractor @Inject constructor(
    private val catalogRepository: CatalogRepository
){
    fun getCatalogWithProducts(catalogId: Long) = catalogRepository.getCatalogWithProducts(catalogId)

    fun getCompanyCatalogs(companyId: Long) = catalogRepository.getCompanyCatalogs(companyId)
}