package com.vkr.vkrmobile.model.data.net.response.news

import com.google.gson.annotations.SerializedName
import java.util.*

class NewsResponse (
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("text") val text: String,
    @SerializedName("logo") val logo: String,
    @SerializedName("datePost") val datePost: Date,
    @SerializedName("dateEnd") val dateEnd: Date?
)