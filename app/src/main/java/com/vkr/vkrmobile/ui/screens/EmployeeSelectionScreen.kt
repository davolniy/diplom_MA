package com.vkr.vkrmobile.ui.screens

import androidx.fragment.app.Fragment
import com.vkr.vkrmobile.ui.fragment.service.EmployeeSelectionFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class EmployeeSelectionScreen(
    val companyId: Long,
    val productId: Long,
    val selectedDate: Long
) : SupportAppScreen() {
    override fun getFragment(): Fragment? {
        return EmployeeSelectionFragment.newInstance(companyId, productId, selectedDate)
    }
}