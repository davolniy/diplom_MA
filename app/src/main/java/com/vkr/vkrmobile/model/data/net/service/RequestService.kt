package com.vkr.vkrmobile.model.data.net.service

import com.vkr.vkrmobile.model.data.ApiMethods
import com.vkr.vkrmobile.model.data.net.ApiResponse
import com.vkr.vkrmobile.model.data.net.ApiResponseEmpty
import com.vkr.vkrmobile.model.data.net.response.request.CompanyRequestTypesResponse
import com.vkr.vkrmobile.model.data.net.response.request.DynamicField
import com.vkr.vkrmobile.model.data.net.response.request.RequestResponse
import com.vkr.vkrmobile.model.data.net.response.request.RequestTyoeResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RequestService {
    @GET(ApiMethods.Requests.GetCompaniesRequestTypes)
    fun getCompanyRequestTypes() : Single<ApiResponse<List<CompanyRequestTypesResponse>>>

    @GET(ApiMethods.Requests.GetRequests)
    fun getRequests() : Single<ApiResponse<List<RequestResponse>>>

    @GET(ApiMethods.Requests.GetRequestType)
    fun getRequestType(
        @Query("requestTypeId") requestTypeId: Long
    ) : Single<ApiResponse<RequestTyoeResponse>>

    @POST(ApiMethods.Requests.MakeRequest)
    fun makeRequest(
        @Query("requestTypeId") requestTypeId: Long,
        @Body fields: List<DynamicField>
    ) : Single<ApiResponseEmpty>
}