package com.vkr.vkrmobile.ui.fragment.chat

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.vkr.vkrmobile.R
import com.vkr.vkrmobile.di.AppScopes
import com.vkr.vkrmobile.domain.config.GlobalConfig
import com.vkr.vkrmobile.model.data.net.response.chat.ChatResponse
import com.vkr.vkrmobile.presentation.chat.ChatsPresenter
import com.vkr.vkrmobile.presentation.chat.ChatsView
import com.vkr.vkrmobile.ui.fragment.global.BaseFragment
import com.vkr.vkrmobile.ui.global.list.chat.ListChatsAdapterDelegate
import kotlinx.android.synthetic.main.chats_fragment.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import toothpick.Toothpick
import javax.inject.Inject

class ChatsFragment : BaseFragment(), ChatsView {
    override val layoutRes: Int
        get() = R.layout.chats_fragment

    @InjectPresenter
    lateinit var presenter: ChatsPresenter

    @Inject
    lateinit var globalConfig: GlobalConfig

    private val adapter by lazy { ChatsAdapter() }

    @ProvidePresenter
    fun providePresenter(): ChatsPresenter = Toothpick
        .openScopes(AppScopes.MAIN_ACTIVITY_SCOPE, AppScopes.CHATS_SCOPE)
        .getInstance(ChatsPresenter::class.java)

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Toothpick.openScopes(AppScopes.MAIN_ACTIVITY_SCOPE, AppScopes.CHATS_SCOPE).let { Toothpick.inject(this, it) }
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        Toothpick.closeScope(AppScopes.CHATS_SCOPE)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        chatsRecyclerView.run {
            layoutManager = LinearLayoutManager(context)
            adapter = this@ChatsFragment.adapter
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

        chatsSwipeRefreshLayout.setOnRefreshListener {
            adapter.clearData()
            presenter.refresh()
        }
    }

    override fun showProgress(show: Boolean) {
        chatsSwipeRefreshLayout.isRefreshing = show
    }

    override fun setData(data: List<ChatResponse>) {
        adapter.setData(data)
    }

    private inner class ChatsAdapter() : ListDelegationAdapter<MutableList<ChatResponse>>() {

        init {
            items = mutableListOf()
            delegatesManager
                .addDelegate(ListChatsAdapterDelegate())
            setHasStableIds(true)
        }

        fun setData(data: List<ChatResponse>) {
            items.clear()
            items.addAll(data)
            notifyDataSetChanged()
        }

        fun clearData() {
            items.clear()
            notifyDataSetChanged()
        }

        override fun getItemId(position: Int): Long {
            return items.get(position).id
        }
    }
}