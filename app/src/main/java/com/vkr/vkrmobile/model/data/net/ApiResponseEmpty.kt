package ru.feedback.app.model.data.net

import com.google.gson.annotations.SerializedName

data class ApiResponseEmpty constructor(
    @SerializedName("Success") val isSuccess: Boolean?,
    @SerializedName("ErrorMessage") val errorMessage: String?,
    @SerializedName("ErrorCode") val errorCode: Int?
)
