package com.vkr.vkrmobile.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.vkr.vkrmobile.R
import com.vkr.vkrmobile.di.AppScopes
import com.vkr.vkrmobile.domain.config.GlobalConfig
import com.vkr.vkrmobile.presentation.global.GlobalMenuController
import com.vkr.vkrmobile.presentation.main.MainPresenter
import com.vkr.vkrmobile.presentation.main.MainView
import com.vkr.vkrmobile.ui.fragment.global.BaseFragment
import com.vkr.vkrmobile.ui.fragment.home.BottomMenuFragment
import com.vkr.vkrmobile.ui.fragment.home.HomeFragment.Companion.PLUS_BUTTON
import com.vkr.vkrmobile.ui.fragment.profile.ProfileMenuFragment
import com.vkr.vkrmobile.ui.global.BaseActivity
import com.vkr.vkrmobile.ui.global.setColorView
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import toothpick.Toothpick
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    companion object {
        const val LAUNCH_REQUEST_CODE = 1
    }

    override val layoutRes = R.layout.activity_main
    override val navigator = SupportAppNavigator(this, R.id.mainContainer)

    private val currentFragment
        get() = supportFragmentManager.findFragmentById(R.id.mainContainer)

    @Inject
    lateinit var globalConfig: GlobalConfig

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @Inject
    lateinit var menuController: GlobalMenuController

    private var menuStateDisposable = CompositeDisposable()

    @ProvidePresenter
    fun providePresenter(): MainPresenter = Toothpick
        .openScopes(AppScopes.LAUNCH_SCOPE, AppScopes.APP_SCOPE)
        .getInstance(MainPresenter::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        Toothpick.openScopes(AppScopes.LAUNCH_SCOPE, AppScopes.APP_SCOPE).apply {
            Toothpick.inject(this@MainActivity, this)
        }

        setTheme(R.style.AppTheme)
        super.onCreate(null)
        presenter.initialize()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing)
            Toothpick.closeScope(AppScopes.MAIN_ACTIVITY_SCOPE)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            openNavDrawer(false)
        } else {
            val currentFragment = currentFragment
            when (currentFragment) {
                is BaseFragment -> currentFragment.onBackPressed()
                else -> presenter.onBackPressed()
            }
        }
    }

    override fun initMenu() {
        when(globalConfig.configurationParams.menuViewMode) {
            "Top" -> {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, GravityCompat.START)
                GravityCompat.START
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.drawerContainer, ProfileMenuFragment())
                    .commitNow()
            }
            "FAB" -> {
                plusButton.visibility = View.VISIBLE
                plusButton.setColorView(globalConfig.accentColor)
                plusButton.setOnClickListener {
                    supportFragmentManager.let {
                        (BottomMenuFragment.newInstance(false) as BottomMenuFragment).show(
                            it,
                            PLUS_BUTTON
                        )
                    }
                }
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
            else -> {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }
    }

    private fun openNavDrawer(open: Boolean) {
        if (open) drawerLayout.openDrawer(GravityCompat.START)
        else drawerLayout.closeDrawer(GravityCompat.START)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == LAUNCH_REQUEST_CODE) {

        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        menuStateDisposable.add(menuController.state.subscribe { openNavDrawer(it) })
    }

    override fun onPause() {
        menuStateDisposable.dispose()
        super.onPause()
    }
}
