package com.vkr.vkrmobile.model.data.net.response.review

import com.google.gson.annotations.SerializedName
import java.util.*

class UserReviewResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("value") val value: Long,
    @SerializedName("text") val text: String,
    @SerializedName("createDate") val createDate: Date,
    @SerializedName("userName") val userName: String
)