package com.vkr.vkrmobile.ui.screens

import androidx.fragment.app.Fragment
import com.vkr.vkrmobile.ui.fragment.service.ServicesFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class ServicesScreen : SupportAppScreen() {
    override fun getFragment(): Fragment? {
        return ServicesFragment()
    }
}