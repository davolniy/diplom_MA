package com.vkr.vkrmobile.model.interactor

import com.vkr.vkrmobile.model.repository.OrderRepository
import javax.inject.Inject

class OrderInteractor @Inject constructor(
    private val orderRepository: OrderRepository
) {
    fun getOrders() = orderRepository.getOrders()

    fun makeOrder(cartId: Long) = orderRepository.makeOrder(cartId)
}