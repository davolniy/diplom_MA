package com.vkr.vkrmobile.ui.fragment.company

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.vkr.vkrmobile.R
import com.vkr.vkrmobile.di.AppScopes
import com.vkr.vkrmobile.domain.config.GlobalConfig
import com.vkr.vkrmobile.model.data.net.response.cart.CartItemResponse
import com.vkr.vkrmobile.model.data.net.response.catalog.CatalogResponse
import com.vkr.vkrmobile.model.data.net.response.company.CompanyResponse
import com.vkr.vkrmobile.model.data.net.response.news.NewsResponse
import com.vkr.vkrmobile.model.data.net.response.product.ProductResponse
import com.vkr.vkrmobile.presentation.company.CompanyPresenter
import com.vkr.vkrmobile.presentation.company.CompanyView
import com.vkr.vkrmobile.ui.fragment.global.BaseFragment
import com.vkr.vkrmobile.ui.global.list.news.CardsActionAdapterDelegate
import com.vkr.vkrmobile.ui.global.list.product.ListProductAdapterDelegate
import com.vkr.vkrmobile.ui.global.loadImage
import kotlinx.android.synthetic.main.company_fragment.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import toothpick.Toothpick
import toothpick.config.Module
import javax.inject.Inject

class CompanyFragment : BaseFragment(), CompanyView {

    companion object {
        private const val COMPANY_ID_ARGUMENT = "company_id_argument"

        fun newInstance(companyId: Long) : Fragment {
            return CompanyFragment().apply {
                arguments = Bundle().apply {
                    putLong(COMPANY_ID_ARGUMENT, companyId)
                }
            }
        }
    }

    private val companyId get() = arguments?.getLong(COMPANY_ID_ARGUMENT) ?: 0L

    override val layoutRes: Int
        get() = R.layout.company_fragment

    @InjectPresenter
    lateinit var presenter: CompanyPresenter

    @Inject
    lateinit var globalConfig: GlobalConfig

    private val productsAdapter by lazy { ProductsAdapter() }
    private val actionsAdapter by lazy { ActionsAdapter() }

    @ProvidePresenter
    fun providePresenter(): CompanyPresenter = Toothpick
        .openScopes(AppScopes.MAIN_ACTIVITY_SCOPE, AppScopes.COMPANY_SCOPE)
        .apply {
            installModules(object : Module() {
                init {
                    val initParams = CompanyPresenter.InitParams(companyId)
                    bind(CompanyPresenter.InitParams::class.java).toInstance(initParams)
                }
            })
        }
        .getInstance(CompanyPresenter::class.java)

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Toothpick.openScopes(AppScopes.MAIN_ACTIVITY_SCOPE, AppScopes.COMPANY_SCOPE).let { Toothpick.inject(this, it) }
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        Toothpick.closeScope(AppScopes.COMPANY_SCOPE)
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

        topToolbarLayout.run {
            setBackgroundColor(globalConfig.accentColor)
        }

        catalogProductsRecyclerView.run {
            layoutManager = LinearLayoutManager(context)
            adapter = this@CompanyFragment.productsAdapter
        }

        companyActionsRecyclerView.run {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = this@CompanyFragment.actionsAdapter
        }

        catalogTabs.setSelectedTabIndicatorColor(globalConfig.accentColor)
        context?.run {
            catalogTabs.setTabTextColors(ContextCompat.getColor(this, R.color.primaryBlack99), globalConfig.accentColor)
        }

        catalogTabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let { presenter.selectCatalogCategory(tab.tag as Long) }
            }
        })

    }

    override fun setCatalogs(catalogs: List<CatalogResponse>, selectedCategoryId: Long) {
        catalogTabs.removeAllTabs()
        catalogs.forEach { category ->
            catalogTabs.newTab()
                .setText(category.name)
                .setTag(category.id)
                .apply {
                    catalogTabs.addTab(this, category.id == selectedCategoryId)
                }
        }
    }

    override fun setCatalogItems(items: List<ProductResponse>) {
        productsAdapter.setData(items)
    }

    override fun setCompanyActions(items: List<NewsResponse>) {
        actionsAdapter.setData(items)
    }

    override fun setData(data: CompanyResponse) {
        toolbarText.text = data.name
        companyAddress.text = data.address
        context?.run {
            if (globalConfig.configurationParams.companyLogoViewMode) {
                companyLogo.loadImage(
                    url = data.logo,
                    placeholderDrawable = ContextCompat.getDrawable(this, R.drawable.ic_placeholder)
                )
            }
            companyRating.text = String.format(getString(R.string.companyScorePlaceHolder), data.reviewScore)
        }
    }

    private inner class ActionsAdapter() : ListDelegationAdapter<MutableList<NewsResponse>>() {
        init {
            items = mutableListOf()
            delegatesManager
                .addDelegate(CardsActionAdapterDelegate())
        }

        fun setData(data: List<NewsResponse>) {
            items.clear()
            items.addAll(data)
            notifyDataSetChanged()
        }
    }

    private inner class ProductsAdapter() : ListDelegationAdapter<MutableList<ProductResponse>>() {
        init {
            items = mutableListOf()
            delegatesManager
                .addDelegate(ListProductAdapterDelegate(
                    globalConfig.accentColor
                ) { presenter.addProductToCart(it) })
        }

        fun setData(data: List<ProductResponse>) {
            items.clear()
            items.addAll(data)
            notifyDataSetChanged()
        }
    }
}