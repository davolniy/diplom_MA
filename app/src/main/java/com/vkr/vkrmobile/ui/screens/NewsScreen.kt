package com.vkr.vkrmobile.ui.screens

import androidx.fragment.app.Fragment
import com.vkr.vkrmobile.ui.fragment.news.NewsFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class NewsScreen : SupportAppScreen() {
    override fun getFragment(): Fragment? {
        return NewsFragment()
    }
}