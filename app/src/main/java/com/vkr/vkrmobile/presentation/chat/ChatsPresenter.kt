package com.vkr.vkrmobile.presentation.chat

import com.vkr.vkrmobile.model.interactor.ChatInteractor
import com.vkr.vkrmobile.model.navigation.AppRouter
import com.vkr.vkrmobile.model.system.ErrorHandler
import com.vkr.vkrmobile.model.system.SystemMessageNotifier
import com.vkr.vkrmobile.presentation.global.BasePresenter
import com.vkr.vkrmobile.presentation.global.GlobalMenuController
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class ChatsPresenter @Inject constructor(
    private val router: AppRouter,
    private val chatInteractor: ChatInteractor,
    private val errorHandler: ErrorHandler,
    private val systemMessageNotifier: SystemMessageNotifier,
    private val globalMenuController: GlobalMenuController
) : BasePresenter<ChatsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        refresh()
    }

    fun onBackPressed() {
        router.exit()
    }

    fun onNavigationClick() {
        globalMenuController.open()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun refresh() {
        loadChats()
    }

    fun loadChats() = chatInteractor
        .getChats()
        .doOnSubscribe { viewState.showProgress(true) }
        .doOnTerminate { viewState.showProgress(false) }
        .subscribe(
            {
                viewState.setData(it)
            },
            {
                errorHandler.proceed(it) { message ->
                    systemMessageNotifier.send(message)
                }
            })

}