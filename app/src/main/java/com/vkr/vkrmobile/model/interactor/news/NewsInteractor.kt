package com.vkr.vkrmobile.model.interactor.news

import com.vkr.vkrmobile.domain.config.GlobalConfig
import com.vkr.vkrmobile.model.repository.news.NewsRepository
import javax.inject.Inject

class NewsInteractor @Inject constructor(
    private val newsRepository: NewsRepository
) {
    fun getAllNews(page: Int, pageSize: Int) = newsRepository.getAllNews(page, pageSize)
}