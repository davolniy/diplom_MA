package com.vkr.vkrmobile.model.interactor

import com.vkr.vkrmobile.model.repository.CompanyRepository
import javax.inject.Inject


class CompanyInteractor @Inject constructor(
    private val companyRepository: CompanyRepository
) {
    fun getBranchCompanies() = companyRepository.getBranchCompanies()

    fun getParentCompanies() = companyRepository.getParentCompanies()

    fun getCompaniesWithBranches() = companyRepository.getCompaniesWithBranches()

    fun getCompany(companyId: Long) = companyRepository.getCompany(companyId)
}