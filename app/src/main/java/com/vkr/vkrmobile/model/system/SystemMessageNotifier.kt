package com.vkr.vkrmobile.model.system

import com.jakewharton.rxrelay3.PublishRelay
import io.reactivex.rxjava3.core.Observable

class SystemMessageNotifier {

    private val notifierRelay = PublishRelay.create<String>()

    val notifier: Observable<String> = notifierRelay

    fun send(message: String?) = notifierRelay.accept(message)
}
