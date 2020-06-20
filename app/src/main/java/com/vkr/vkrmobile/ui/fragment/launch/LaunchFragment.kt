package com.vkr.vkrmobile.ui.fragment.launch

import android.os.Bundle
import android.view.View
import com.vkr.vkrmobile.R
import com.vkr.vkrmobile.di.AppScopes
import com.vkr.vkrmobile.presentation.launch.LaunchPresenter
import com.vkr.vkrmobile.presentation.launch.LaunchView
import com.vkr.vkrmobile.ui.fragment.global.BaseFragment
import kotlinx.android.synthetic.main.launch_fragment.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import toothpick.Toothpick

class LaunchFragment : BaseFragment(), LaunchView {

    override val layoutRes: Int
        get() = R.layout.launch_fragment

    @InjectPresenter
    lateinit var presenter: LaunchPresenter

    @ProvidePresenter
    fun providePresenter(): LaunchPresenter = Toothpick
        .openScope(AppScopes.LAUNCH_SCOPE)
        .getInstance(LaunchPresenter::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logoImageView.setImageResource(R.drawable.app_logo)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}