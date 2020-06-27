package com.vkr.vkrmobile.ui.fragment.profile

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import com.vkr.vkrmobile.R
import com.vkr.vkrmobile.di.AppScopes
import com.vkr.vkrmobile.domain.config.AuthConfig
import com.vkr.vkrmobile.domain.config.GlobalConfig
import com.vkr.vkrmobile.model.data.net.response.auth.ProfileResponse
import com.vkr.vkrmobile.presentation.profile.EditProfilePresenter
import com.vkr.vkrmobile.presentation.profile.EditProfileView
import com.vkr.vkrmobile.ui.fragment.global.BaseFragment
import kotlinx.android.synthetic.main.edit_profile_fragment.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import toothpick.Toothpick
import javax.inject.Inject

class EditProfileFragment : BaseFragment(), EditProfileView {
    override val layoutRes: Int
        get() = R.layout.edit_profile_fragment

    @InjectPresenter
    lateinit var presenter: EditProfilePresenter

    @Inject
    lateinit var globalConfig: GlobalConfig

    @Inject
    lateinit var authConfig: AuthConfig

    @ProvidePresenter
    fun providePresenter(): EditProfilePresenter = Toothpick
        .openScopes(AppScopes.MAIN_ACTIVITY_SCOPE, AppScopes.EDIT_PROFILE_SCOPE)
        .getInstance(EditProfilePresenter::class.java)

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Toothpick.openScopes(AppScopes.MAIN_ACTIVITY_SCOPE, AppScopes.EDIT_PROFILE_SCOPE).let { Toothpick.inject(this, it) }
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        Toothpick.closeScope(AppScopes.EDIT_PROFILE_SCOPE)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (globalConfig.configurationParams.menuViewMode == "Top") {
            navigationToolBarButton.run {
                visibility = View.VISIBLE
                setOnClickListener { presenter.onNavigationClick() }
            }
        }

        toolbarLayout.run {
            setBackgroundColor(globalConfig.accentColor)
        }

        emailEditTextLayout.boxStrokeColor = globalConfig.accentColor
        nameEditTextLayout.boxStrokeColor = globalConfig.accentColor
        emailEditTextLayout.hintTextColor = ColorStateList.valueOf(globalConfig.accentColor)
        nameEditTextLayout.hintTextColor = ColorStateList.valueOf(globalConfig.accentColor)

        saveButton.setBackgroundColor(globalConfig.accentColor)
        signOut.setBackgroundColor(globalConfig.accentColor)

        saveButton.setOnClickListener {
            presenter.save(
                name = nameEditText.text.toString(),
                email = emailEditText.text.toString(),
                gender = maleGenderRadioButton.isChecked
            )
        }

        signOut.setOnClickListener {
            presenter.logout()
        }

        maleGenderRadioButton.highlightColor = globalConfig.accentColor
        femaleGenderRadioButton.highlightColor = globalConfig.accentColor
    }

    override fun setData(profile: ProfileResponse) {
        emailEditText.setText(profile.name)
        nameEditText.setText(profile.email)
        if (profile.gender == false) {
            femaleGenderRadioButton.isChecked = true
        } else {
            maleGenderRadioButton.isChecked = true
        }
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            profileSwipeRefreshLayout.isEnabled = show
            profileSwipeRefreshLayout.isRefreshing = show
        } else {
            profileSwipeRefreshLayout.isRefreshing = show
            profileSwipeRefreshLayout.isEnabled = show
        }
    }
}