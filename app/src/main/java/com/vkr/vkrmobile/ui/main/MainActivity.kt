package com.vkr.vkrmobile.ui.main

import android.os.Bundle
import com.vkr.vkrmobile.R
import com.vkr.vkrmobile.di.AppScopes
import com.vkr.vkrmobile.presentation.main.MainPresenter
import com.vkr.vkrmobile.presentation.main.MainView
import com.vkr.vkrmobile.ui.global.BaseActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import toothpick.Toothpick

class MainActivity : BaseActivity(), MainView {

    override val layoutRes = R.layout.activity_main
    override val navigator = SupportAppNavigator(this, R.id.mainContainer)

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter = Toothpick
        .openScope(AppScopes.LAUNCH_SCOPE)
        .getInstance(MainPresenter::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        Toothpick.openScopes(AppScopes.LAUNCH_SCOPE).apply {
            Toothpick.inject(this@MainActivity, this)
        }

        setTheme(R.style.AppTheme)
        super.onCreate(null)
        presenter.initialize()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
//        menuStateDisposable = menuController.state.subscribe { openNavDrawer(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing)
            Toothpick.closeScope(AppScopes.MAIN_ACTIVITY_SCOPE)
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

}
