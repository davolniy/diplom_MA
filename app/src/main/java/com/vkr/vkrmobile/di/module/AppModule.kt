package com.vkr.vkrmobile.di.module

import android.content.Context
import com.vkr.vkrmobile.di.provider.RetrofitProvider
import com.vkr.vkrmobile.model.data.net.service.CatalogService
import com.vkr.vkrmobile.model.data.net.service.CompanyService
import com.vkr.vkrmobile.model.data.net.service.NewsService
import retrofit2.Retrofit
import toothpick.config.Module
import ru.feedback.app.di.provider.service.CatalogServiceProvider
import ru.feedback.app.di.provider.service.CompanyServiceProvider
import ru.feedback.app.di.provider.service.NewsServiceProvider

class AppModule() : Module() {
    init {
        // Network
        bind(Retrofit::class.java).toProvider(RetrofitProvider::class.java).singleton()

        // Services
        bind(NewsService::class.java).toProvider(NewsServiceProvider::class.java).singleton()
        bind(CompanyService::class.java).toProvider(CompanyServiceProvider::class.java).singleton()
        bind(CatalogService::class.java).toProvider(CatalogServiceProvider::class.java).singleton()
    }
}