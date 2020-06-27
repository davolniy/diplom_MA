package com.vkr.vkrmobile.di.module

import com.vkr.vkrmobile.di.provider.RetrofitProvider
import com.vkr.vkrmobile.di.provider.service.*
import com.vkr.vkrmobile.domain.config.MenuScreenConfig
import com.vkr.vkrmobile.model.data.net.service.*
import com.vkr.vkrmobile.model.interactor.AuthInteractor
import com.vkr.vkrmobile.model.repository.AuthRepository
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
        bind(CartService::class.java).toProvider(CartServiceProvider::class.java).singleton()
        bind(ChatService::class.java).toProvider(ChatServiceProvider::class.java).singleton()
        bind(OrderService::class.java).toProvider(OrderServiceProvider::class.java).singleton()
        bind(ProductService::class.java).toProvider(ProductServiceProvider::class.java).singleton()
        bind(RequestService::class.java).toProvider(RequestServiceProvider::class.java).singleton()
        bind(ReviewService::class.java).toProvider(ReviewServiceProvider::class.java).singleton()
        bind(ServiceService::class.java).toProvider(ServiceServiceProvider::class.java).singleton()

        // Repositories
    }
}