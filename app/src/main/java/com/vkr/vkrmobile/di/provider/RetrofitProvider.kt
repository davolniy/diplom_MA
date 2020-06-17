package com.vkr.vkrmobile.di.provider

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Provider

class RetrofitProvider @Inject constructor(
    private val apiPath: String,
    private val gson: Gson
) : Provider<Retrofit> {

    override fun get(): Retrofit = Retrofit.Builder()
        .baseUrl(apiPath)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}
