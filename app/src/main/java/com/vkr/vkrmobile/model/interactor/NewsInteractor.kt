package com.vkr.vkrmobile.model.interactor

import com.vkr.vkrmobile.model.repository.NewsRepository
import javax.inject.Inject

class NewsInteractor @Inject constructor(
    private val newsRepository: NewsRepository
) {
    fun getAllNews(page: Int, pageSize: Int) = newsRepository.getAllNews(page, pageSize)

    fun getAllActions(page: Int, pageSize: Int) = newsRepository.getAllActions(page, pageSize)

    fun getCompanyActions(companyId: Long) = newsRepository.getCompanyActions(companyId)
}