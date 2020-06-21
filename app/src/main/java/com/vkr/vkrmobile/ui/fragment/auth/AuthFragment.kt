package com.vkr.vkrmobile.ui.fragment.auth

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.vkr.vkrmobile.R
import com.vkr.vkrmobile.di.AppScopes
import com.vkr.vkrmobile.domain.config.GlobalConfig
import com.vkr.vkrmobile.presentation.auth.AuthPresenter
import com.vkr.vkrmobile.presentation.auth.AuthView
import com.vkr.vkrmobile.ui.fragment.global.BaseFragment
import kotlinx.android.synthetic.main.auth_fragment.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import toothpick.Toothpick
import javax.inject.Inject

class AuthFragment : BaseFragment(), AuthView {

    override val layoutRes: Int
        get() = R.layout.auth_fragment

    @InjectPresenter
    lateinit var presenter: AuthPresenter

    @Inject
    lateinit var globalConfig: GlobalConfig

    @ProvidePresenter
    fun providePresenter(): AuthPresenter = Toothpick
        .openScopes(AppScopes.MAIN_ACTIVITY_SCOPE, AppScopes.AUTH_SCOPE)
        .getInstance(AuthPresenter::class.java)

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Toothpick.openScopes(AppScopes.MAIN_ACTIVITY_SCOPE, AppScopes.AUTH_SCOPE).let { Toothpick.inject(this, it) }
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        Toothpick.closeScope(AppScopes.AUTH_SCOPE)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (!globalConfig.configurationParams.authRequired) {
            toolbar.run {
                visibility = View.VISIBLE
                val iconDrawable = resources.getDrawable(R.drawable.ic_arrow_left, context?.theme)
                iconDrawable.setTint(ContextCompat.getColor(context, R.color.primaryWhite))
                navigationIcon = iconDrawable
                setNavigationOnClickListener {
                    presenter.onBackPressed()
                }
                setBackgroundColor(globalConfig.accentColor)
                setTitle(R.string.authTitle)
            }
        } else {
            toolbar.visibility = View.GONE
        }

        phoneNumberEditTextLayout.boxStrokeColor = globalConfig.accentColor
        authPasswordEditTextLayout.boxStrokeColor = globalConfig.accentColor
        phoneNumberEditTextLayout.hintTextColor = ColorStateList.valueOf(globalConfig.accentColor)
        authPasswordEditTextLayout.hintTextColor = ColorStateList.valueOf(globalConfig.accentColor)

        registrationButton.setBackgroundColor(globalConfig.accentColor)
        signInButton.setBackgroundColor(globalConfig.accentColor)

        registrationButton.setOnClickListener {
            presenter.registration(phoneNumberEditText.text.toString(), authPasswordEditText.text.toString())
        }

        signInButton.setOnClickListener {
            presenter.authorization(phoneNumberEditText.text.toString(), authPasswordEditText.text.toString())
        }
    }
}