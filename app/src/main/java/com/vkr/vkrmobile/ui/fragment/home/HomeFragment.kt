package com.vkr.vkrmobile.ui.fragment.home

import com.vkr.vkrmobile.R
import com.vkr.vkrmobile.di.AppScopes
import com.vkr.vkrmobile.ui.fragment.global.BaseFragment
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.core.view.size
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.vkr.vkrmobile.domain.config.GlobalConfig
import com.vkr.vkrmobile.domain.config.MenuScreenConfig
import com.vkr.vkrmobile.presentation.home.HomePresenter
import com.vkr.vkrmobile.presentation.home.HomeView
import com.vkr.vkrmobile.ui.global.setColorView
import kotlinx.android.synthetic.main.home_fragment.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.terrakok.cicerone.android.support.SupportAppScreen
import toothpick.Toothpick
import java.util.*
import javax.inject.Inject

class HomeFragment : BaseFragment(), HomeView {

    companion object {
        private const val STATE_CURRENT_TAB_INDEX = "state_current_tab_index"
        const val PLUS_BUTTON = "plus_button"
    }

    override val layoutRes = R.layout.home_fragment

    @Inject
    lateinit var menuScreenConfig: MenuScreenConfig

    @Inject
    lateinit var globalConfig: GlobalConfig

    @InjectPresenter
    lateinit var presenter: HomePresenter

    private var currentTabIndex = 0

    private lateinit var tabs: HashMap<Int, SupportAppScreen>

    @ProvidePresenter
    fun providePresenter(): HomePresenter = Toothpick
        .openScopes(AppScopes.MAIN_ACTIVITY_SCOPE, AppScopes.HOME_SCOPE)
        .getInstance(HomePresenter::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        Toothpick.openScopes(AppScopes.MAIN_ACTIVITY_SCOPE, AppScopes.HOME_SCOPE)
            .let { Toothpick.inject(this, it) }
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tabs = hashMapOf()

        bottomNavigationView.run {
            labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_SELECTED
            menuScreenConfig.customMenu.menuItems.forEachIndexed() { index, customMenuItem ->
                this.menu.add(Menu.NONE, index, Menu.NONE, customMenuItem.title).run {
                    if (customMenuItem.screen != null) {
                        tabs[this.itemId] = customMenuItem.screen
                    }
                }
            }

            if (menuScreenConfig.plusButtonEnabled) {
                plusButton.run {
                    setColorView(globalConfig.accentColor)
                    visibility = View.GONE
                    setOnClickListener { openBottomFragment() }
                }
            }
            setColorView(globalConfig.accentColor)

            if (savedInstanceState != null) {
                currentTabIndex = savedInstanceState.getInt(STATE_CURRENT_TAB_INDEX, 0)

                if (menu.size() > 0) {
                    selectTab(menu.getItem(0).itemId)
                }
            }

            setOnNavigationItemSelectedListener { item ->
                selectTab(item.itemId)
                true
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(STATE_CURRENT_TAB_INDEX, currentTabIndex)
        super.onSaveInstanceState(outState)
    }

    private fun openBottomFragment() {
//        activity?.supportFragmentManager?.let { (BottomMenuFragment.newInstance(true) as BottomMenuFragment).show(it, "bottom_menu") }
        fragmentManager?.let {
            (BottomMenuFragment.newInstance(true) as BottomMenuFragment).show(
                it,
                PLUS_BUTTON
            )
        }
    }

    private fun loadFragment(tabId: Int) {
        val fragment = tabs[tabId]?.fragment

        fragment?.run {
            fragmentManager?.apply {
                beginTransaction()
                .replace(R.id.homeContainer, fragment)
                .commit()
            }
        }
    }

    override fun selectTab(tabId: Int) {
        val newTab = bottomNavigationView.menu.findItem(tabId)

        if (!newTab.title.isNullOrEmpty()) {
            currentTabIndex = tabId
            loadFragment(tabId)
        }
    }
}

