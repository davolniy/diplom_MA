package com.vkr.vkrmobile.di.module

import android.content.Context
import com.google.gson.Gson
import com.vkr.vkrmobile.di.ApiPath
import com.vkr.vkrmobile.di.provider.ApiPathProvider
import com.vkr.vkrmobile.di.provider.GsonProvider
import com.vkr.vkrmobile.di.provider.OkHttpClientProvider
import com.vkr.vkrmobile.domain.config.AuthConfig
import com.vkr.vkrmobile.domain.config.GlobalConfig
import com.vkr.vkrmobile.model.data.auth.AuthHolder
import com.vkr.vkrmobile.model.data.auth.CurrentUserHolder
import toothpick.config.Module
import com.vkr.vkrmobile.model.interactor.LaunchInteractor
import com.vkr.vkrmobile.model.navigation.AppRouter
import com.vkr.vkrmobile.model.system.SystemMessageNotifier
import com.vkr.vkrmobile.presentation.global.GlobalMenuController
import okhttp3.OkHttpClient
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder

class LaunchModule(context: Context) : Module() {
    init {
        // Global
        bind(Context::class.java).toInstance(context)

        // Config
        bind(GlobalConfig::class.java).singleton()

        // Network
        bind(Gson::class.java).toProvider(GsonProvider::class.java).singleton()
        bind(OkHttpClient::class.java).toProvider(OkHttpClientProvider::class.java).singleton()
        bind(String::class.java).withName(ApiPath::class.java).toProvider(ApiPathProvider::class.java).singleton()

        // Routing
        val cicerone = Cicerone.create(AppRouter())
        bind(AppRouter::class.java).toInstance(cicerone.router)
        bind(NavigatorHolder::class.java).toInstance(cicerone.navigatorHolder)

        // Launch
        bind(LaunchInteractor::class.java).singleton()

        // Auth
        bind(AuthHolder::class.java).to(AuthConfig::class.java).singleton()
        bind(CurrentUserHolder::class.java).to(AuthConfig::class.java).singleton()

        // Message notification
        bind(SystemMessageNotifier::class.java).toInstance(SystemMessageNotifier())

        // Main screen
        bind(GlobalMenuController::class.java).toInstance(GlobalMenuController())
    }
}