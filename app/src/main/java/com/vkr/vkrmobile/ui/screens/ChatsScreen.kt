package com.vkr.vkrmobile.ui.screens

import androidx.fragment.app.Fragment
import com.vkr.vkrmobile.ui.fragment.chat.ChatsFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class ChatsScreen  : SupportAppScreen() {
    override fun getFragment(): Fragment? {
        return ChatsFragment()
    }
}