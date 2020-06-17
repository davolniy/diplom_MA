package com.vkr.vkrmobile.ui.screens

import androidx.fragment.app.Fragment
import com.vkr.vkrmobile.ui.launch.LaunchFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class LaunchScreen : SupportAppScreen() {
    override fun getFragment(): Fragment? {
        return LaunchFragment.newInstance()
    }
}