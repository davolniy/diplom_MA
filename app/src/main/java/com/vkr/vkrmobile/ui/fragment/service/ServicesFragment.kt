package com.vkr.vkrmobile.ui.fragment.service

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.vkr.vkrmobile.R
import com.vkr.vkrmobile.di.AppScopes
import com.vkr.vkrmobile.domain.config.GlobalConfig
import com.vkr.vkrmobile.model.data.net.response.service.ServiceResponse
import com.vkr.vkrmobile.presentation.service.ServicesPresenter
import com.vkr.vkrmobile.presentation.service.ServicesView
import com.vkr.vkrmobile.ui.fragment.global.BaseFragment
import com.vkr.vkrmobile.ui.global.list.service.ListServicesAdapterDelegate
import kotlinx.android.synthetic.main.services_fragment.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import toothpick.Toothpick
import javax.inject.Inject

class ServicesFragment : BaseFragment(), ServicesView {
    override val layoutRes: Int
        get() = R.layout.services_fragment

    @InjectPresenter
    lateinit var presenter: ServicesPresenter

    @Inject
    lateinit var globalConfig: GlobalConfig

    private val adapter by lazy { ServicesAdapter() }

    @ProvidePresenter
    fun providePresenter(): ServicesPresenter = Toothpick
        .openScopes(AppScopes.MAIN_ACTIVITY_SCOPE, AppScopes.NEWS_SCOPE)
        .getInstance(ServicesPresenter::class.java)

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Toothpick.openScopes(AppScopes.MAIN_ACTIVITY_SCOPE, AppScopes.NEWS_SCOPE)
            .let { Toothpick.inject(this, it) }
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

        toolbarLayout.run {
            setBackgroundColor(globalConfig.accentColor)
        }

        servicesRecyclerView.run {
            layoutManager = LinearLayoutManager(context)
            adapter = this@ServicesFragment.adapter
        }

        servicesSwipeRefreshLayout.setOnRefreshListener {
            adapter.clearData()
            presenter.refresh()
        }
    }

    override fun showProgress(show: Boolean) {
        servicesSwipeRefreshLayout.isRefreshing = show
    }

    override fun setData(data: List<ServiceResponse>) {
        adapter.setData(data)
    }

    private inner class ServicesAdapter() : ListDelegationAdapter<MutableList<ServiceResponse>>() {
        init {
            items = mutableListOf()
            delegatesManager
                .addDelegate(ListServicesAdapterDelegate())
        }

        fun clearData() {
            items.clear()
            notifyDataSetChanged()
        }

        fun setData(data: List<ServiceResponse>) {
            items.clear()
            items.addAll(data)
            notifyDataSetChanged()
        }
    }
}