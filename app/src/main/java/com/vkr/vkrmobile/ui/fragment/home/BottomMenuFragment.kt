package com.vkr.vkrmobile.ui.fragment.home

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.vkr.vkrmobile.R
import com.vkr.vkrmobile.di.AppScopes
import com.vkr.vkrmobile.domain.config.GlobalConfig
import com.vkr.vkrmobile.domain.config.MenuScreenConfig
import com.vkr.vkrmobile.domain.menu.CustomMenuItem
import com.vkr.vkrmobile.presentation.home.BottomMenuPresenter
import com.vkr.vkrmobile.presentation.home.BottomMenuView
import com.vkr.vkrmobile.ui.fragment.global.BaseDialogFragment
import com.vkr.vkrmobile.ui.global.list.menu.MenuRowAdapterDelegate
import kotlinx.android.synthetic.main.bottom_menu_fragment.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import toothpick.Toothpick
import javax.inject.Inject

class BottomMenuFragment : BaseDialogFragment(), BottomMenuView {

    companion object {
        private const val FOR_PLUS_BUTTON_ARGUMENT = "for_plus_button_argument"

        fun newInstance(forPlusButton: Boolean) : Fragment {
            return BottomMenuFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(FOR_PLUS_BUTTON_ARGUMENT, forPlusButton)
                }
            }
        }
    }

    override val layoutRes = R.layout.bottom_menu_fragment

    @Inject
    lateinit var globalConfig: GlobalConfig

    @Inject
    lateinit var menuScreenConfig: MenuScreenConfig

    @InjectPresenter
    lateinit var presenter: BottomMenuPresenter

    private val forPlusButton get() = arguments?.getBoolean(FOR_PLUS_BUTTON_ARGUMENT) ?: false
    private val adapter by lazy { BottomMenuAdapter() }

    @ProvidePresenter
    fun providePresenter(): BottomMenuPresenter = Toothpick
        .openScopes(AppScopes.MAIN_ACTIVITY_SCOPE, AppScopes.BOTTOM_MENU_SCOPE)
        .getInstance(BottomMenuPresenter::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        Toothpick.openScopes(AppScopes.MAIN_ACTIVITY_SCOPE, AppScopes.BOTTOM_MENU_SCOPE)
            .let { Toothpick.inject(this, it) }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), theme)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bottomMenuRecyclerView.run {
            layoutManager = LinearLayoutManager(context)
            adapter = this@BottomMenuFragment.adapter
        }

        adapter.setData((if (forPlusButton) menuScreenConfig.customMenu.plusMenuItems else menuScreenConfig.customMenu.menuItems))
    }

    override fun setMenu(items: List<CustomMenuItem>) {
        adapter.setData(items)
    }

    private inner class BottomMenuAdapter : ListDelegationAdapter<MutableList<CustomMenuItem>>() {
        init {
            items = mutableListOf()
            delegatesManager.addDelegate(MenuRowAdapterDelegate {
                presenter.onMenuRowClick(it)
                dismiss()
            })
        }

        fun setData(data: List<CustomMenuItem>) {
            items.clear()
            items.addAll(data)
            notifyDataSetChanged()
        }
    }
}