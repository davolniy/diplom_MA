package com.vkr.vkrmobile.model.repository.launch

import android.util.Log
import com.google.gson.Gson
import com.vkr.vkrmobile.BuildConfig
import com.vkr.vkrmobile.domain.config.GlobalConfig
import com.vkr.vkrmobile.model.data.net.response.launch.AppConfigurationResponse
import com.vkr.vkrmobile.model.data.net.service.LaunchService
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class LaunchRepository @Inject constructor(
    private val globalConfig: GlobalConfig,
    private val gson: Gson
) {

    companion object {
        const val DEFAULT_URL = "http://10.0.2.2:44351/api/"
    }

    private val service: LaunchService
        get() = Retrofit
            .Builder()
            .baseUrl(DEFAULT_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(LaunchService::class.java)

    fun initialize(): Single<AppConfigurationResponse> =
        service.getAppInit(BuildConfig.APPLICATION_ID)
            .doOnSuccess {
                var apiUrl = it.apiUrl.replace("localhost:44350", "10.0.2.2:44351")
                globalConfig.apiUrl = apiUrl
                globalConfig.configurationParams = it.appConfigurationParams
            }
            .doOnError {
                Log.d("TestApi", it.message ?: "Initialize error")
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}