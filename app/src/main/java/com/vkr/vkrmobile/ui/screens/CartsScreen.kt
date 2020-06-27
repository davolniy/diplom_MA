package com.vkr.vkrmobile.ui.screens

import androidx.fragment.app.Fragment
import com.vkr.vkrmobile.ui.fragment.cart.CartsFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class CartsScreen : SupportAppScreen() {
    override fun getFragment(): Fragment? {
        return CartsFragment()
    }
}