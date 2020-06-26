package com.vkr.vkrmobile.model.data.net.service

import com.vkr.vkrmobile.model.data.ApiMethods
import com.vkr.vkrmobile.model.data.net.ApiResponse
import com.vkr.vkrmobile.model.data.net.ApiResponseEmpty
import com.vkr.vkrmobile.model.data.net.response.order.OrderResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface OrderService {
    @GET(ApiMethods.Orders.GetOrders)
    fun getOrders() : Single<ApiResponse<List<OrderResponse>>>

    @POST(ApiMethods.Orders.MakeOrder)
    fun makeOrder(
        @Query("cartId") cartId: Long
    ) : Single<ApiResponseEmpty>
}