package com.vkr.vkrmobile.model.data.net.service

import com.vkr.vkrmobile.model.data.ApiMethods
import com.vkr.vkrmobile.model.data.net.ApiResponse
import com.vkr.vkrmobile.model.data.net.ApiResponseEmpty
import com.vkr.vkrmobile.model.data.net.response.employee.EmployeeWithTimeCellsResponse
import com.vkr.vkrmobile.model.data.net.response.service.ServiceResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.*

interface ServiceService {
    @GET(ApiMethods.Services.GetEmployees)
    fun getEmployees(
        @Query("productId") productId: Long,
        @Query("selectedDate") selectedDate: Date
    ) : Single<ApiResponse<List<EmployeeWithTimeCellsResponse>>>

    @GET(ApiMethods.Services.GetServices)
    fun getServices() : Single<ApiResponse<List<ServiceResponse>>>

    @POST(ApiMethods.Services.MakeService)
    fun makeService(
        @Query("companyId") companyId: Long,
        @Query("productId") productId: Long,
        @Query("employeeId") employeeId: Long,
        @Query("serviceDate") serviceDate: Date
    ) : Single<ApiResponseEmpty>
}