package com.vkr.vkrmobile.ui.screens

import androidx.fragment.app.Fragment
import com.vkr.vkrmobile.ui.fragment.auth.AuthFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class AuthScreen : SupportAppScreen() {
    override fun getFragment(): Fragment? {
        return AuthFragment()
    }
}