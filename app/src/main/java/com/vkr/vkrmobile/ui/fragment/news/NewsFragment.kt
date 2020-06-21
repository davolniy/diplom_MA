package com.vkr.vkrmobile.ui.fragment.news

import android.os.Bundle
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
import com.vkr.vkrmobile.ui.global.list.news.CardsNewsAdapterDelegate
import com.vkr.vkrmobile.ui.global.list.news.ExpandableCardsNewsAdapterDelegate
import com.vkr.vkrmobile.ui.global.list.news.StaggeredCardsNewsAdapterDelegate
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

        newsSwipeRefreshLayout.setOnRefreshListener {
            adapter.clearData()
            presenter.refresh()
        }
    }

    override fun setData(data: List<NewsResponse>) {
        adapter.setData(data)
    }

    override fun clearData() {
        adapter.clearData()
    }

    private inner class NewsAdapter() : ListDelegationAdapter<MutableList<NewsResponse>>() {
        private var page = 1

        init {
            items = mutableListOf()
            delegatesManager
                .addDelegate(CardsNewsAdapterDelegate(globalConfig.configurationParams.menuViewMode))
                .addDelegate(StaggeredCardsNewsAdapterDelegate(globalConfig.configurationParams.menuViewMode))
                .addDelegate(ExpandableCardsNewsAdapterDelegate(globalConfig.configurationParams.menuViewMode))
        }

        fun setData(data: List<NewsResponse>) {
            items.addAll(data)
            notifyDataSetChanged()
            page++
        }

        fun clearData() {
            items.clear()
            notifyDataSetChanged()
            page = 0
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            super.onBindViewHolder(holder, position)

            if (position == items.size - NewsPresenter.NEWS_PAGE_SIZE / 3) {
                presenter.loadPage(page)
            }
        }
    }
}