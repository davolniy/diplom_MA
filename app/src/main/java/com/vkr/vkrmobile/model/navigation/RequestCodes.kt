package com.vkr.vkrmobile.model.navigation

import java.util.concurrent.atomic.AtomicInteger

object RequestCodes {

    private val counter = AtomicInteger()

    val INIT = counter.incrementAndGet()
    val INIT_MENU = counter.incrementAndGet()
}
