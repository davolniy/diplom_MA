package com.vkr.vkrmobile.ui.screens

import androidx.fragment.app.Fragment
import com.vkr.vkrmobile.model.data.net.response.launch.ConfigurationFunctions
import com.vkr.vkrmobile.ui.fragment.home.HomeFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class HomeScreen() : SupportAppScreen() {
    override fun getFragment(): Fragment? {
        return HomeFragment()
    }
}