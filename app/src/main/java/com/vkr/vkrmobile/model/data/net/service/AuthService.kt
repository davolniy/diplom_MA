package com.vkr.vkrmobile.model.data.net.service

import com.vkr.vkrmobile.model.data.ApiMethods
import com.vkr.vkrmobile.model.data.net.ApiResponse
import com.vkr.vkrmobile.model.data.net.ApiResponseEmpty
import com.vkr.vkrmobile.model.data.net.response.auth.AuthResponse
import com.vkr.vkrmobile.model.data.net.response.auth.ProfileResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {
    @GET(ApiMethods.Users.Authorization)
    fun authorization(
        @Query("phoneNumber") packageName: String,
        @Query("password") password: String
    ): Single<ApiResponse<AuthResponse>>

    @GET(ApiMethods.Users.Registration)
    fun registration(
        @Query("phoneNumber") packageName: String,
        @Query("password") password: String
    ): Single<ApiResponse<AuthResponse>>

    @POST(ApiMethods.Users.EditProfile)
    fun editProfile(
        @Query("name") packageName: String,
        @Query("email") email: String,
        @Query("gender") gender: Boolean?
    ): Single<ApiResponse<ProfileResponse>>

    @GET(ApiMethods.Users.GetProfile)
    fun getProfile(): Single<ApiResponse<ProfileResponse>>
}