package com.vkr.vkrmobile.model.data.net.response.request

import com.google.gson.annotations.SerializedName
import java.util.*

class RequestResponse (
    @SerializedName("id") val id: Long,
    @SerializedName("companyName") val companyName: String,
    @SerializedName("requestTypeName") val requestTypeName: String,
    @SerializedName("createData") val createDate: Date
)