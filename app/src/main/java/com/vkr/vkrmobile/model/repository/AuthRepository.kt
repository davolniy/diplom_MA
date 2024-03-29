package com.vkr.vkrmobile.model.repository

import com.jakewharton.rxrelay3.BehaviorRelay
import com.vkr.vkrmobile.model.data.auth.AuthHolder
import com.vkr.vkrmobile.model.data.auth.CurrentUserHolder
import com.vkr.vkrmobile.model.data.net.response.auth.AuthResponse
import com.vkr.vkrmobile.model.data.net.response.auth.ProfileResponse
import com.vkr.vkrmobile.model.data.net.service.AuthService
import com.vkr.vkrmobile.ui.global.fetchData
import com.vkr.vkrmobile.ui.global.fetchResult
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val service: AuthService,
    private val authHolder: AuthHolder,
    private val userHolder: CurrentUserHolder
) {
    val isSignedIn get() = !authHolder.token.isNullOrEmpty()

    private val authChangesRelay = BehaviorRelay.createDefault<Boolean>(isSignedIn)

    val authChangesObservable: Observable<Boolean> = authChangesRelay
        .hide()
        .observeOn(AndroidSchedulers.mainThread())

    fun authorize(phone: String, password: String) = service.authorization(phone, password)
        .fetchData()
        .doOnSuccess { saveAuthData(it) }
        .doOnError { }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun registration(phone: String, password: String) = service.registration(phone, password)
        .fetchData()
        .doOnSuccess { saveAuthData(it) }
        .doOnError { }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun editProfile(name: String, email: String, gender: Boolean?) = service.editProfile(name, email, gender)
        .fetchData()
        .doOnSuccess { saveProfileData(it) }
        .doOnError { }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun getProfile() = service.getProfile()
        .fetchData()
        .doOnSuccess { saveProfileData(it) }
        .doOnError { }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun logout() {
        clearAuthData()
    }

    fun clearAuthData() {
        authHolder.token = null
        userHolder.userId = 0L
        userHolder.userName = ""
        userHolder.userEmail = ""
        userHolder.userGender = null
        authChangesRelay.accept(false)
    }

    private fun saveAuthData(data: AuthResponse) {
        authHolder.token = data.token
        userHolder.userId = data.userId ?: 0L
        authChangesRelay.accept(true)
    }

    private fun saveProfileData(data: ProfileResponse) {
        userHolder.userName = data.name
        userHolder.userEmail = data.email
        userHolder.userGender = data.gender
    }
}