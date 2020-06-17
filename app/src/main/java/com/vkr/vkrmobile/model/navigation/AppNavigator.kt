package com.vkr.vkrmobile.model.navigation

import androidx.fragment.app.FragmentActivity
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class AppNavigator(val activity: FragmentActivity, val containerId: Int) : SupportAppNavigator(activity, containerId)