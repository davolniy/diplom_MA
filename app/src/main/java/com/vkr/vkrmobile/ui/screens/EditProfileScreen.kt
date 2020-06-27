package com.vkr.vkrmobile.ui.screens

import androidx.fragment.app.Fragment
import com.vkr.vkrmobile.ui.fragment.profile.EditProfileFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class EditProfileScreen : SupportAppScreen() {
    override fun getFragment(): Fragment? {
        return EditProfileFragment()
    }
}