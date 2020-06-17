package com.vkr.vkrmobile

import android.app.Application
import com.vkr.vkrmobile.di.AppScopes
import com.vkr.vkrmobile.di.module.LaunchModule
import toothpick.Toothpick
import toothpick.configuration.Configuration

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        initToothpick()
        initLaunchModule()
    }

    private fun initToothpick() {
        Toothpick.setConfiguration(Configuration.forProduction())
    }

    private fun initLaunchModule() {
        Toothpick.openScope(AppScopes.LAUNCH_SCOPE).installModules(LaunchModule(this))
    }
}