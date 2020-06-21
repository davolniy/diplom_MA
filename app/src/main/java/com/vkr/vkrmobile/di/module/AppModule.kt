package com.vkr.vkrmobile.di.module

import com.vkr.vkrmobile.di.provider.RetrofitProvider
import com.vkr.vkrmobile.di.provider.service.AuthServiceProvider
import com.vkr.vkrmobile.di.provider.service.CatalogServiceProvider
import com.vkr.vkrmobile.di.provider.service.CompanyServiceProvider
import com.vkr.vkrmobile.di.provider.service.NewsServiceProvider
import com.vkr.vkrmobile.domain.config.MenuScreenConfig
import com.vkr.vkrmobile.model.data.net.service.AuthService
import com.vkr.vkrmobile.model.data.net.service.CatalogService
import com.vkr.vkrmobile.model.data.net.service.CompanyService
import com.vkr.vkrmobile.model.data.net.service.NewsService
import com.vkr.vkrmobile.model.interactor.auth.AuthInteractor
import com.vkr.vkrmobile.model.repository.auth.AuthRepository
import com.vkr.vkrmobile.model.system.ErrorHandler
import retrofit2.Retrofit
import toothpick.config.Module

class AppModule() : Module() {
    init {
        // Configs
        bind(MenuScreenConfig::class.java).singleton()

        // Network
        bind(Retrofit::class.java).toProvider(RetrofitProvider::class.java).singleton()

        // Error handler
        bind(ErrorHandler::class.java).singleton()

        // Auth
        bind(AuthInteractor::class.java).singleton()
        bind(AuthRepository::class.java).singleton()

        // Services
        bind(AuthService::class.java).toProvider(AuthServiceProvider::class.java).singleton()
        bind(NewsService::class.java).toProvider(NewsServiceProvider::class.java).singleton()
        bind(CompanyService::class.java).toProvider(CompanyServiceProvider::class.java).singleton()
        bind(CatalogService::class.java).toProvider(CatalogServiceProvider::class.java).singleton()
    }
}