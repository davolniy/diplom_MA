package com.vkr.vkrmobile.model.user

import com.google.gson.annotations.SerializedName

import java.io.Serializable

data class User(@SerializedName("Token") val token: String?) : Serializable
