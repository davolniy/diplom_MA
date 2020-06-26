package com.vkr.vkrmobile.model.data.net.response.employee

import com.google.gson.annotations.SerializedName

class EmployeeResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("companyId") val companyId: Long
)