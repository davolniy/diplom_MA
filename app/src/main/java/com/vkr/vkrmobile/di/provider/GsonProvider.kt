package com.vkr.vkrmobile.di.provider


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vkr.vkrmobile.model.data.net.ServerDateConverter
import java.util.*
import javax.inject.Inject
import javax.inject.Provider

class GsonProvider @Inject internal constructor() : Provider<Gson> {
    override fun get(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(Date::class.java, ServerDateConverter)
            .create()
    }
}
