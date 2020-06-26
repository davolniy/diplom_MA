package com.vkr.vkrmobile.model.data.net.service

import com.vkr.vkrmobile.model.data.ApiMethods
import com.vkr.vkrmobile.model.data.net.ApiResponse
import com.vkr.vkrmobile.model.data.net.response.news.NewsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET(ApiMethods.News.GetAllNews)
    fun getAllNews(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Single<ApiResponse<List<NewsResponse>>>

    @GET(ApiMethods.News.GetAllActions)
    fun getAllActions(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Single<ApiResponse<List<NewsResponse>>>

    @GET(ApiMethods.News.GetCompanyActions)
    fun getCompanyActions(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Single<ApiResponse<List<NewsResponse>>>

    @GET(ApiMethods.News.GetNews)
    fun getNews(
        @Query("newsId") newsId: Long
    ): Single<ApiResponse<List<NewsResponse>>>
}