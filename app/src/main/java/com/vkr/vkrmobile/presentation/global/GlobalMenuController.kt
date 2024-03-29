package com.vkr.vkrmobile.presentation.global

import com.jakewharton.rxrelay3.PublishRelay
import io.reactivex.rxjava3.core.Observable

class GlobalMenuController {
    private val stateRelay = PublishRelay.create<Boolean>()

    val state: Observable<Boolean> = stateRelay
    fun open() = stateRelay.accept(true)
    fun close() = stateRelay.accept(false)
}