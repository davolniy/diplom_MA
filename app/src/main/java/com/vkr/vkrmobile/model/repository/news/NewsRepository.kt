package com.vkr.vkrmobile.model.repository.news

import com.vkr.vkrmobile.model.data.net.service.NewsService
import com.vkr.vkrmobile.ui.global.fetchData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsService: NewsService
) {
    fun getAllNews(page: Int, pageSize: Int) = newsService
        .getAllNews(page, pageSize)
        .fetchData()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}