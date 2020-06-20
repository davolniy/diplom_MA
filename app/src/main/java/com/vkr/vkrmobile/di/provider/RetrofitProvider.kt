package com.vkr.vkrmobile.di.provider

import com.google.gson.Gson
import com.vkr.vkrmobile.di.ApiPath
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Provider

class RetrofitProvider @Inject constructor(
    @ApiPath private val apiPath: String,
    private val okHttpClient: OkHttpClient,
    private val gson: Gson
) : Provider<Retrofit> {

    override fun get(): Retrofit = Retrofit.Builder()
        .baseUrl(apiPath)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
}
