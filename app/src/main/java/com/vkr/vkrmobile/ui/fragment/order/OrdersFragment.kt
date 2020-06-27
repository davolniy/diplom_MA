package com.vkr.vkrmobile.ui.fragment.order

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.vkr.vkrmobile.R
import com.vkr.vkrmobile.di.AppScopes
import com.vkr.vkrmobile.domain.config.GlobalConfig
import com.vkr.vkrmobile.model.data.net.response.order.OrderResponse
import com.vkr.vkrmobile.presentation.order.OrdersPresenter
import com.vkr.vkrmobile.presentation.order.OrdersView
import com.vkr.vkrmobile.ui.fragment.global.BaseFragment
import com.vkr.vkrmobile.ui.global.list.cart.OrderListAdapterDelegate
import kotlinx.android.synthetic.main.orders_fragment.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import toothpick.Toothpick
import javax.inject.Inject

class OrdersFragment : BaseFragment(), OrdersView {
    override val layoutRes: Int
        get() = R.layout.orders_fragment

    @InjectPresenter
    lateinit var presenter: OrdersPresenter

    @Inject
    lateinit var globalConfig: GlobalConfig

    private val adapter by lazy { OrdersAdapter() }

    @ProvidePresenter
    fun providePresenter(): OrdersPresenter = Toothpick
        .openScopes(AppScopes.MAIN_ACTIVITY_SCOPE, AppScopes.ORDERS_SCOPE)
        .getInstance(OrdersPresenter::class.java)

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Toothpick.openScopes(AppScopes.MAIN_ACTIVITY_SCOPE, AppScopes.ORDERS_SCOPE)
            .let { Toothpick.inject(this, it) }
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        Toothpick.closeScope(AppScopes.ORDERS_SCOPE)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (globalConfig.configurationParams.menuViewMode == "Top") {
            navigationToolBarButton.run {
                visibility = View.VISIBLE
                setOnClickListener { presenter.onNavigationClick() }
            }
        }

        toolbarLayout.run {
            setBackgroundColor(globalConfig.accentColor)
        }

        ordersRecyclerView.run {
            layoutManager = LinearLayoutManager(context)
            adapter = this@OrdersFragment.adapter
        }

        ordersSwipeRefreshLayout.setOnRefreshListener {
            adapter.clearData()
            presenter.refresh()
        }
    }

    override fun showProgress(show: Boolean) {
        ordersSwipeRefreshLayout.isRefreshing = show
    }

    override fun setData(data: List<OrderResponse>) {
        adapter.setData(data)
    }

    private inner class OrdersAdapter() : ListDelegationAdapter<MutableList<OrderResponse>>() {
        init {
            items = mutableListOf()
            delegatesManager
                .addDelegate(OrderListAdapterDelegate())
        }

        fun clearData() {
            items.clear()
            notifyDataSetChanged()
        }

        fun setData(data: List<OrderResponse>) {
            items.clear()
            items.addAll(data)
            notifyDataSetChanged()
        }
    }
}