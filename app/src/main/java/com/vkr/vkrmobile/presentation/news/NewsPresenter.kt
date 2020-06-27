package com.vkr.vkrmobile.presentation.news

import com.vkr.vkrmobile.model.interactor.NewsInteractor
import com.vkr.vkrmobile.model.navigation.AppRouter
import com.vkr.vkrmobile.model.system.ErrorHandler
import com.vkr.vkrmobile.model.system.SystemMessageNotifier
import com.vkr.vkrmobile.presentation.global.BasePresenter
import com.vkr.vkrmobile.presentation.global.GlobalMenuController
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class NewsPresenter @Inject constructor(
    private val router: AppRouter,
    private val newsInteractor: NewsInteractor,
    private val errorHandler: ErrorHandler,
    private val systemMessageNotifier: SystemMessageNotifier,
    private val globalMenuController: GlobalMenuController
) : BasePresenter<NewsView>() {
    companion object {
        const val NEWS_PAGE_SIZE = 15
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        refresh()
    }

    fun onBackPressed() {
        router.exit()
    }

    fun onNavigationClick() {
        globalMenuController.open()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun loadPage(page: Int) {
        loadNews(page, NEWS_PAGE_SIZE)
    }

    fun refresh() {
        loadNews(1, NEWS_PAGE_SIZE)
    }

    fun loadNews(page: Int, pageSize: Int) = newsInteractor
        .getAllNews(page, pageSize)
        .doOnSubscribe { viewState.showProgress(true) }
        .doOnTerminate { viewState.showProgress(false) }
        .subscribe(
            {
                viewState.setData(it, page, pageSize)
            },
            {
                errorHandler.proceed(it) { message ->
                    systemMessageNotifier.send(message)
                }
            })

}