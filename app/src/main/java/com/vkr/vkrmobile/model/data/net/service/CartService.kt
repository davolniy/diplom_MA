package com.vkr.vkrmobile.model.data.net.service

import com.vkr.vkrmobile.model.data.ApiMethods
import com.vkr.vkrmobile.model.data.net.ApiResponse
import com.vkr.vkrmobile.model.data.net.ApiResponseEmpty
import com.vkr.vkrmobile.model.data.net.response.cart.CartResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CartService {
    @GET(ApiMethods.Carts.GetAllCarts)
    fun getAllCarts(): Single<ApiResponse<List<CartResponse>>>

    @POST(ApiMethods.Carts.AddToCart)
    fun addToCart(
        @Query("companyId") companyId: Long,
        @Query("productId") catalogId: Long
    ): Single<ApiResponseEmpty>

    @POST(ApiMethods.Carts.UpdateProductCount)
    fun updateProductCount(
        @Query("companyId") companyId: Long,
        @Query("productId") catalogId: Long,
        @Query("count") count: Int
    ): Single<ApiResponseEmpty>
}