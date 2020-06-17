package com.vkr.vkrmobile.di.module

import android.content.Context
import com.google.gson.Gson
import com.vkr.vkrmobile.domain.config.GlobalConfig
import ru.feedback.app.di.provider.server.GsonProvider
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import toothpick.config.Module
import com.vkr.vkrmobile.model.interactor.LaunchInteractor
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class LaunchModule(context: Context) : Module() {
    init {
        // Global
        bind(Context::class.java).toInstance(context)

        // Config
        bind(GlobalConfig::class.java).singleton()

        // Network
        bind(Gson::class.java).toProvider(GsonProvider::class.java).singleton()

        // Routing
        val cicerone = Cicerone.create()
        bind(Router::class.java).toInstance(cicerone.router)
        bind(NavigatorHolder::class.java).toInstance(cicerone.navigatorHolder)

        // Launch
        bind(LaunchInteractor::class.java).singleton()
    }
}