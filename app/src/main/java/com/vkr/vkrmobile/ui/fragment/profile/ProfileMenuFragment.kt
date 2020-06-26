package com.vkr.vkrmobile.ui.fragment.profile

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.vkr.vkrmobile.R
import com.vkr.vkrmobile.di.AppScopes
import com.vkr.vkrmobile.domain.config.GlobalConfig
import com.vkr.vkrmobile.domain.config.MenuScreenConfig
import com.vkr.vkrmobile.domain.menu.CustomMenuItem
import com.vkr.vkrmobile.presentation.profile.ProfileMenuPresenter
import com.vkr.vkrmobile.presentation.profile.ProfileMenuView
import com.vkr.vkrmobile.ui.fragment.global.BaseDialogFragment
import com.vkr.vkrmobile.ui.global.list.menu.MenuRowAdapterDelegate
import kotlinx.android.synthetic.main.profile_menu_fragment.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import toothpick.Toothpick
import javax.inject.Inject

class ProfileMenuFragment : BaseDialogFragment(), ProfileMenuView {
    override val layoutRes = R.layout.profile_menu_fragment

    @Inject
    lateinit var menuScreenConfig: MenuScreenConfig

    @InjectPresenter
    lateinit var presenter: ProfileMenuPresenter

    @Inject
    lateinit var globalConfig: GlobalConfig

    private val adapter by lazy { MenuAdapter() }

    @ProvidePresenter
    fun providePresenter(): ProfileMenuPresenter = Toothpick
        .openScopes(AppScopes.MAIN_ACTIVITY_SCOPE, AppScopes.PROFILE_SCOPE)
        .getInstance(ProfileMenuPresenter::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        Toothpick.openScopes(AppScopes.MAIN_ACTIVITY_SCOPE, AppScopes.PROFILE_SCOPE)
            .let { Toothpick.inject(this, it) }
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        menuRecyclerView.run {
            layoutManager = LinearLayoutManager(context)
            adapter = this@ProfileMenuFragment.adapter
        }

        adapter.setData(menuScreenConfig.customMenu.menuItems)

        menuLogoutItem.setOnClickListener {
            presenter.logout()
        }

        toolbarLayout.run {
            setBackgroundColor(globalConfig.accentColor)
        }
    }

    override fun setMenu(items: List<CustomMenuItem>) {
        adapter.setData(items)
    }

    private inner class MenuAdapter : ListDelegationAdapter<MutableList<CustomMenuItem>>() {
        init {
            items = mutableListOf()
            delegatesManager.addDelegate(MenuRowAdapterDelegate {
                presenter.onMenuRowClick(it)
            })
        }

        fun setData(data: List<CustomMenuItem>) {
            items.clear()
            items.addAll(data)
            notifyDataSetChanged()
        }
    }
}