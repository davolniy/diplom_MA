package com.vkr.vkrmobile.ui.fragment.global

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import moxy.MvpAppCompatFragment
import moxy.MvpDelegateHolder

abstract class BaseFragment : MvpAppCompatFragment() {
    @get:LayoutRes
    protected abstract val layoutRes: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(layoutRes, container, false)

    open fun onBackPressed() {}
}