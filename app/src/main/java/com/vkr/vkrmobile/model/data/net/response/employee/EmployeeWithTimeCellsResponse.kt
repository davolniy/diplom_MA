package com.vkr.vkrmobile.model.data.net.response.employee

import com.google.gson.annotations.SerializedName
import java.util.*

class EmployeeWithTimeCellsResponse(
    @SerializedName("employee") val employee: EmployeeResponse,
    @SerializedName("timeCells") val timeCells: List<Date>
)