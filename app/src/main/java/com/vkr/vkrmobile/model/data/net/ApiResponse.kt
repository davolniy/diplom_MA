package ru.feedback.app.model.data.net

import com.google.gson.annotations.SerializedName

open class ApiResponse<T> constructor(
    @SerializedName("success") val isSuccess: Boolean? = null,
    @SerializedName("data") val data: T? = null,
    @SerializedName("errorMessage") val errorMessage: String? = null,
    @SerializedName("errorCode") val errorCode: Int? = null
)
