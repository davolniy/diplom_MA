package com.vkr.vkrmobile.model.interactor

import com.vkr.vkrmobile.model.repository.CartRepository
import javax.inject.Inject

class CartInteractor @Inject constructor(
    private val cartRepository: CartRepository
){
    fun getAllCarts() = cartRepository.getAllCarts()

    fun addToCart(companyId: Long, productId: Long) = cartRepository.addToCart(companyId, productId)

    fun updateProductCount(companyId: Long, productId: Long, count: Int) = cartRepository.updateProductCount(companyId, productId, count)
}