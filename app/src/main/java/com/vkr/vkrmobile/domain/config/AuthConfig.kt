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
    }

    private fun getSharedPreferences(prefsName: String) =
        context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)

    override var token: String?
        get() = getSharedPreferences(AUTH_DATA).getString(KEY_TOKEN, "")
        set(value) = getSharedPreferences(AUTH_DATA).edit().putString(KEY_TOKEN, value).apply()

    override var userId: Long
        get() = getSharedPreferences(AUTH_DATA).getLong(KEY_USER_ID, 0L)
        set(value) = getSharedPreferences(AUTH_DATA).edit().putLong(KEY_USER_ID, value).apply()
}