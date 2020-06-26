package com.vkr.vkrmobile.ui.screens

import androidx.fragment.app.Fragment
import com.vkr.vkrmobile.ui.fragment.company.CompaniesFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class CompaniesScreen : SupportAppScreen() {
    override fun getFragment(): Fragment? {
        return CompaniesFragment()
    }
}