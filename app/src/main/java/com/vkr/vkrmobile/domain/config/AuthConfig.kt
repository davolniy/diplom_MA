package com.vkr.vkrmobile.domain.config

import android.content.Context
import com.vkr.vkrmobile.model.data.auth.AuthHolder
import com.vkr.vkrmobile.model.data.auth.CurrentUserHolder
import javax.inject.Inject

class AuthConfig @Inject constructor(
    private val context: Context
): AuthHolder, CurrentUserHolder {
    companion object {
        private const val AUTH_DATA = "auth_data"
        private const val KEY_TOKEN = "ad_token"
        private const val KEY_USER_ID = "ad_user_id"
        private const val KEY_USER_NAME = "ad_user_name"
        private const val KEY_USER_EMAIL = "ad_user_email"
        private const val KEY_USER_GENDER = "ad_user_gender"
    }

    private fun getSharedPreferences(prefsName: String) =
        context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)

    override var token: String?
        get() = getSharedPreferences(AUTH_DATA).getString(KEY_TOKEN, "")
        set(value) = getSharedPreferences(AUTH_DATA).edit().putString(KEY_TOKEN, value).apply()

    override var userId: Long
        get() = getSharedPreferences(AUTH_DATA).getLong(KEY_USER_ID, 0L)
        set(value) = getSharedPreferences(AUTH_DATA).edit().putLong(KEY_USER_ID, value).apply()

    override var userName: String
        get() = getSharedPreferences(AUTH_DATA).getString(KEY_USER_NAME, "") ?: ""
        set(value) = getSharedPreferences(AUTH_DATA).edit().putString(KEY_USER_NAME, value).apply()

    override var userEmail: String
        get() = getSharedPreferences(AUTH_DATA).getString(KEY_USER_EMAIL, "") ?: ""
        set(value) = getSharedPreferences(AUTH_DATA).edit().putString(KEY_USER_EMAIL, value).apply()

    override var userGender: Boolean?
        get() = getSharedPreferences(AUTH_DATA).getBoolean(KEY_USER_GENDER, true)
        set(value) = getSharedPreferences(AUTH_DATA).edit().putBoolean(KEY_USER_GENDER, value ?: true).apply()
}