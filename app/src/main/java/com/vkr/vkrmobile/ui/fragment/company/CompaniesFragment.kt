package com.vkr.vkrmobile.ui.fragment.company

import android.os.Bundle
import com.vkr.vkrmobile.R
import com.vkr.vkrmobile.di.AppScopes
import com.vkr.vkrmobile.domain.config.GlobalConfig
import com.vkr.vkrmobile.presentation.company.CompaniesPresenter
import com.vkr.vkrmobile.presentation.company.CompaniesView
import com.vkr.vkrmobile.ui.fragment.global.BaseFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import toothpick.Toothpick
import javax.inject.Inject

class CompaniesFragment : BaseFragment(), CompaniesView {
    override val layoutRes: Int
        get() = R.layout.news_fragment

    @InjectPresenter
    lateinit var presenter: CompaniesPresenter

    @Inject
    lateinit var globalConfig: GlobalConfig

    @ProvidePresenter
    fun providePresenter(): CompaniesPresenter = Toothpick
        .openScopes(AppScopes.MAIN_ACTIVITY_SCOPE, AppScopes.NEWS_SCOPE)
        .getInstance(CompaniesPresenter::class.java)

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Toothpick.openScopes(AppScopes.MAIN_ACTIVITY_SCOPE, AppScopes.NEWS_SCOPE).let { Toothpick.inject(this, it) }
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        Toothpick.closeScope(AppScopes.AUTH_SCOPE)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

//    private inner class CompaniesAdapter() : ListDelegationAdapter<MutableList<Company>>() {
//        init {
//            items = mutableListOf()
//        }
//
//        fun setData(data: List<Company>) {
//            items.clear()
//            items.addAll(data)
//        }
//    }
}