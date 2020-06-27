package com.vkr.vkrmobile.ui.fragment.cart

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.vkr.vkrmobile.R
import com.vkr.vkrmobile.di.AppScopes
import com.vkr.vkrmobile.domain.config.GlobalConfig
import com.vkr.vkrmobile.model.data.net.response.cart.CartResponse
import com.vkr.vkrmobile.presentation.cart.CartsPresenter
import com.vkr.vkrmobile.presentation.cart.CartsView
import com.vkr.vkrmobile.ui.fragment.global.BaseFragment
import com.vkr.vkrmobile.ui.global.list.cart.CartListAdapterDelegate
import kotlinx.android.synthetic.main.carts_fragment.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import toothpick.Toothpick
import javax.inject.Inject

class CartsFragment : BaseFragment(), CartsView {
    override val layoutRes: Int
        get() = R.layout.carts_fragment

    @InjectPresenter
    lateinit var presenter: CartsPresenter

    @Inject
    lateinit var globalConfig: GlobalConfig

    private val adapter by lazy { CartsAdapter() }

    @ProvidePresenter
    fun providePresenter(): CartsPresenter = Toothpick
        .openScopes(AppScopes.MAIN_ACTIVITY_SCOPE, AppScopes.CARTS_SCOPE)
        .getInstance(CartsPresenter::class.java)

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Toothpick.openScopes(AppScopes.MAIN_ACTIVITY_SCOPE, AppScopes.CARTS_SCOPE)
            .let { Toothpick.inject(this, it) }
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        Toothpick.closeScope(AppScopes.CARTS_SCOPE)
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

        cartsRecyclerView.run {
            layoutManager = LinearLayoutManager(context)
            adapter = this@CartsFragment.adapter
        }

        cartsSwipeRefreshLayout.setOnRefreshListener {
            adapter.clearData()
            presenter.refresh()
        }
    }

    override fun setData(data: List<CartResponse>) {
        adapter.setData(data)
    }

    override fun showProgress(show: Boolean) {
        cartsSwipeRefreshLayout.isRefreshing = show
    }

    private inner class CartsAdapter() :
        ListDelegationAdapter<MutableList<CartResponse>>() {
        init {
            items = mutableListOf()
            delegatesManager
                .addDelegate(CartListAdapterDelegate(
                    globalConfig.accentColor,
                    { cartId -> presenter.onOrderClick(cartId) },
                    { companyId, productId, count -> presenter.onCountClick(companyId, productId, count) }
                ))
        }

        fun clearData() {
            items.clear()
            notifyDataSetChanged()
        }

        fun setData(data: List<CartResponse>) {
            items.clear()
            items.addAll(data)
            notifyDataSetChanged()
        }
    }
}