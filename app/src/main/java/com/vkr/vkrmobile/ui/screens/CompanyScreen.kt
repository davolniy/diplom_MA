package com.vkr.vkrmobile.ui.screens

import androidx.fragment.app.Fragment
import com.vkr.vkrmobile.ui.fragment.company.CompanyFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class CompanyScreen(
    private val companyId: Long
) : SupportAppScreen() {
    override fun getFragment(): Fragment? {
        return CompanyFragment.newInstance(companyId)
    }
}