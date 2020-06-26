package com.vkr.vkrmobile.model.data.net.response.company

import com.google.gson.annotations.SerializedName

class CompanyWithBranchesResponse (
    @SerializedName("parentCompany") val parentCompany: CompanyResponse,
    @SerializedName("branches") val branches: List<CompanyResponse>
)