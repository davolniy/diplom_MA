package com.vkr.vkrmobile.model.data.net.service

import com.vkr.vkrmobile.model.data.ApiMethods
import com.vkr.vkrmobile.model.data.net.response.launch.AppConfigurationResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface LaunchService {
    @GET(ApiMethods.Launch.AppInit)
    fun getAppInit(
        @Query("packageName") packageName: String
    ): Single<AppConfigurationResponse>
}