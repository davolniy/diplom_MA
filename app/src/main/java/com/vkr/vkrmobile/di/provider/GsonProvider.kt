package ru.feedback.app.di.provider.server


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import javax.inject.Inject
import javax.inject.Provider

class GsonProvider @Inject internal constructor() : Provider<Gson> {
    override fun get(): Gson {
        return GsonBuilder()
            .create()
    }
}
