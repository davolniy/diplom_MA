package com.vkr.vkrmobile.model.data.net.response.service

import com.google.gson.annotations.SerializedName
import com.vkr.vkrmobile.model.data.net.response.employee.EmployeeResponse
import com.vkr.vkrmobile.model.data.net.response.product.ProductResponse
import java.util.*

class ServiceResponse (
    @SerializedName("id") val id: Long,
    @SerializedName("createDate") val createDate: Date,
    @SerializedName("companyName") val companyName: String,
    @SerializedName("productData") val productData: ProductResponse,
    @SerializedName("employeeData") val employeeData: EmployeeResponse
)