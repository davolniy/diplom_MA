package com.vkr.vkrmobile.model.data.net.service

import com.vkr.vkrmobile.model.data.ApiMethods
import com.vkr.vkrmobile.model.data.net.ApiResponse
import com.vkr.vkrmobile.model.data.net.response.company.CompanyResponse
import com.vkr.vkrmobile.model.data.net.response.company.CompanyWithBranchesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CompanyService {
    @GET(ApiMethods.Companies.GetBranchCompanies)
    fun getBranchCompanies(): Single<ApiResponse<List<CompanyResponse>>>

    @GET(ApiMethods.Companies.GetParentCompanies)
    fun getParentCompanies(): Single<ApiResponse<List<CompanyResponse>>>

    @GET(ApiMethods.Companies.GetCompaniesWithBranches)
    fun getCompaniesWithBranches(): Single<ApiResponse<List<CompanyWithBranchesResponse>>>

    @GET(ApiMethods.Companies.GetCompany)
    fun getCompany(
        @Query("companyId") companyId: Long
    ): Single<ApiResponse<CompanyResponse>>

}