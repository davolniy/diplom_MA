package com.vkr.vkrmobile.presentation.profile

import com.vkr.vkrmobile.model.interactor.AuthInteractor
import com.vkr.vkrmobile.model.navigation.AppRouter
import com.vkr.vkrmobile.model.system.ErrorHandler
import com.vkr.vkrmobile.model.system.SystemMessageNotifier
import com.vkr.vkrmobile.presentation.global.BasePresenter
import com.vkr.vkrmobile.presentation.global.GlobalMenuController
import moxy.InjectViewState
import ru.terrakok.cicerone.android.support.SupportAppScreen
import javax.inject.Inject

@InjectViewState
class EditProfilePresenter @Inject constructor(
    private val router: AppRouter,
    private val errorHandler: ErrorHandler,
    private val systemMessageNotifier: SystemMessageNotifier,
    private val authInteractor: AuthInteractor,
    private val globalMenuController: GlobalMenuController
) : BasePresenter<EditProfileView>()  {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        setData()
    }

    fun onBackPressed() {
        router.exit()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun onNavigationClick() {
        globalMenuController.open()
    }

    fun logout() {
        authInteractor.logout()
    }

    fun setData() = authInteractor.getProfile()
        .doOnSubscribe { viewState.showProgress(true) }
        .doOnTerminate { viewState.showProgress(false) }
        .subscribe({
            viewState.setData(it)
        },{
            errorHandler.proceed(it) { message ->
                systemMessageNotifier.send(message)
            }
        })

    fun save(name: String, email: String, gender: Boolean) {
        authInteractor.editProfile(name, email, gender)
            .doOnSubscribe { viewState.showProgress(true) }
            .doOnTerminate { viewState.showProgress(false) }
            .subscribe({
                systemMessageNotifier.send("Данные успешно сохранены")
                onBackPressed()
            },{
                errorHandler.proceed(it) { message ->
                    systemMessageNotifier.send(message)
                }
            })
    }
}