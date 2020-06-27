package com.vkr.vkrmobile.model.data.net.service

import com.vkr.vkrmobile.model.data.ApiMethods
import com.vkr.vkrmobile.model.data.net.ApiResponse
import com.vkr.vkrmobile.model.data.net.response.catalog.CatalogResponse
import com.vkr.vkrmobile.model.data.net.response.catalog.CatalogWithProductsResponse
import com.vkr.vkrmobile.model.data.net.response.chat.ChatResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CatalogService {
    @GET(ApiMethods.Catalogs.GetCompanyCatalogs)
    fun getCompanyCatalogs(
        @Query("companyId") companyId: Long
    ): Single<ApiResponse<List<CatalogResponse>>>

    @GET(ApiMethods.Catalogs.GetCatalogWithProducts)
    fun getCatalogWithProducts(
        @Query("catalogId") catalogId: Long
    ): Single<ApiResponse<CatalogWithProductsResponse>>
}