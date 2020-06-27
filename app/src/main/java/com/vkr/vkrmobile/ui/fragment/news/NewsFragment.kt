package com.vkr.vkrmobile.ui.fragment.news

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.vkr.vkrmobile.R
import com.vkr.vkrmobile.di.AppScopes
import com.vkr.vkrmobile.domain.config.GlobalConfig
import com.vkr.vkrmobile.model.data.net.response.news.NewsResponse
import com.vkr.vkrmobile.presentation.news.NewsPresenter
import com.vkr.vkrmobile.presentation.news.NewsView
import com.vkr.vkrmobile.ui.fragment.global.BaseFragment
import com.vkr.vkrmobile.ui.global.list.news.*
import kotlinx.android.synthetic.main.news_fragment.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import toothpick.Toothpick
import javax.inject.Inject


class NewsFragment : BaseFragment(), NewsView {

    override val layoutRes: Int
        get() = R.layout.news_fragment

    @InjectPresenter
    lateinit var presenter: NewsPresenter

    @Inject
    lateinit var globalConfig: GlobalConfig

    private val adapter by lazy { NewsAdapter() }

    @ProvidePresenter
    fun providePresenter(): NewsPresenter = Toothpick
        .openScopes(AppScopes.MAIN_ACTIVITY_SCOPE, AppScopes.NEWS_SCOPE)
        .getInstance(NewsPresenter::class.java)

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

        newsRecyclerView.run {
            if (globalConfig.configurationParams.newsViewMode == "StaggeredCards") {
                layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            } else {
                layoutManager = LinearLayoutManager(context)
            }
            adapter = this@NewsFragment.adapter
        }

        if (globalConfig.configurationParams.menuViewMode == "Top") {
            navigationToolBarButton.run {
                visibility = View.VISIBLE
                setOnClickListener { presenter.onNavigationClick() }
            }
        }

        toolbarLayout.run {
            setBackgroundColor(globalConfig.accentColor)
        }

        newsSwipeRefreshLayout.setOnRefreshListener {
            adapter.clearData()
            presenter.refresh()
        }
    }

    override fun showProgress(show: Boolean) {
        newsSwipeRefreshLayout.isRefreshing = show
    }

    override fun setData(data: List<NewsResponse>, page: Int, pageSize: Int) {
        adapter.setData(data, page, pageSize)
    }

    private inner class NewsAdapter() : ListDelegationAdapter<MutableList<NewsResponse>>() {
        private var page: Int = 1

        init {
            items = mutableListOf()
            delegatesManager
                .addDelegate(CardsNewsAdapterDelegate(globalConfig.configurationParams.newsViewMode))
                .addDelegate(StaggeredCardsNewsAdapterDelegate(globalConfig.configurationParams.newsViewMode))
                .addDelegate(ExpandableCardsNewsAdapterDelegate(globalConfig.configurationParams.newsViewMode))
            setHasStableIds(true)
        }

        fun setData(data: List<NewsResponse>, page: Int, pageSize: Int) {
            items.addAll(data)
            notifyItemRangeChanged((page - 1) * pageSize, page * pageSize)
            this.page++
        }

        fun clearData() {
            items.clear()
            notifyDataSetChanged()
            page = 1
        }

        override fun getItemId(position: Int): Long {
            return items.get(position).id
        }

        override fun onBindViewHolder(
            holder: RecyclerView.ViewHolder,
            position: Int,
            payloads: MutableList<Any?>
        ) {
            super.onBindViewHolder(holder, position, payloads)

            if (position == items.size - NewsPresenter.NEWS_PAGE_SIZE / 3) {
                presenter.loadPage(page)
            }
        }
    }
}