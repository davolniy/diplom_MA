package com.vkr.vkrmobile.ui.fragment.company

import android.os.Bundle
import android.view.View
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.vkr.vkrmobile.R
import com.vkr.vkrmobile.di.AppScopes
import com.vkr.vkrmobile.domain.config.GlobalConfig
import com.vkr.vkrmobile.model.data.net.response.company.CompanyWithBranchesResponse
import com.vkr.vkrmobile.presentation.company.CompaniesPresenter
import com.vkr.vkrmobile.presentation.company.CompaniesView
import com.vkr.vkrmobile.ui.fragment.global.BaseFragment
import com.vkr.vkrmobile.ui.global.list.companies.CellsCompaniesAdapterDelegate
import com.vkr.vkrmobile.ui.global.list.companies.ExpandableListCompaniesAdapterDelegate
import com.vkr.vkrmobile.ui.global.list.companies.ListCompaniesAdapterDelegate
import kotlinx.android.synthetic.main.companies_fragment.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import toothpick.Toothpick
import javax.inject.Inject

class CompaniesFragment : BaseFragment(), CompaniesView {
    override val layoutRes: Int
        get() = R.layout.companies_fragment

    @InjectPresenter
    lateinit var presenter: CompaniesPresenter

    @Inject
    lateinit var globalConfig: GlobalConfig

    private val adapter by lazy { CompaniesAdapter() }

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

        if (globalConfig.configurationParams.menuViewMode == "Top") {
            navigationToolBarButton.run {
                visibility = View.VISIBLE
                setOnClickListener { presenter.onBackPressed() }
            }
        }
    }

    override fun setData(data: List<CompanyWithBranchesResponse>) {
        adapter.setData(data)
    }

    override fun showProgress(show: Boolean) {
        companiesSwipeRefreshLayout.isRefreshing = show
    }

    private inner class CompaniesAdapter() : ListDelegationAdapter<MutableList<CompanyWithBranchesResponse>>() {
        init {
            items = mutableListOf()
            delegatesManager
                .addDelegate(ListCompaniesAdapterDelegate(globalConfig.configurationParams.companiesViewMode))
                .addDelegate(CellsCompaniesAdapterDelegate(globalConfig.configurationParams.companiesViewMode))
                .addDelegate(ExpandableListCompaniesAdapterDelegate(globalConfig.configurationParams.companiesViewMode))
        }

        fun setData(data: List<CompanyWithBranchesResponse>) {
            items.clear()
            items.addAll(data)
        }
    }
}