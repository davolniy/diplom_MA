package com.vkr.vkrmobile.model.data.net.service

import com.vkr.vkrmobile.model.data.ApiMethods
import com.vkr.vkrmobile.model.data.net.response.auth.AuthResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthService {
    @GET(ApiMethods.Auth.Authorization)
    fun authorization(
        @Query("phoneNumber") packageName: String,
        @Query("password") password: String
    ): Single<AuthResponse>

    @GET(ApiMethods.Auth.Registration)
    fun registration(
        @Query("phoneNumber") packageName: String,
        @Query("password") password: String
    ): Single<AuthResponse>
}