package com.vkr.vkrmobile.ui.screens

import androidx.fragment.app.Fragment
import com.vkr.vkrmobile.ui.fragment.home.BottomMenuFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class BottomMenuScreen(
    private val forPlusButton: Boolean = false
) : SupportAppScreen() {
    override fun getFragment(): Fragment? {
        return BottomMenuFragment.newInstance(forPlusButton)
    }
}