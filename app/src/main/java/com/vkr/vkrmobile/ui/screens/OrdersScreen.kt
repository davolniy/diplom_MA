package com.vkr.vkrmobile.ui.screens

import androidx.fragment.app.Fragment
import com.vkr.vkrmobile.ui.fragment.order.OrdersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class OrdersScreen : SupportAppScreen() {
    override fun getFragment(): Fragment? {
        return OrdersFragment()
    }
}