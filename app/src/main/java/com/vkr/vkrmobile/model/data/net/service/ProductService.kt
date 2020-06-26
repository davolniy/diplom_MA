package com.vkr.vkrmobile.model.data.net.service

import com.vkr.vkrmobile.model.data.ApiMethods
import com.vkr.vkrmobile.model.data.net.ApiResponse
import com.vkr.vkrmobile.model.data.net.response.product.ProductResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {
    @GET(ApiMethods.Products.GetProduct)
    fun getProduct(
        @Query("productId") productId: Long
    ) : Single<ApiResponse<ProductResponse>>
}