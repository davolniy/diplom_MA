package com.vkr.vkrmobile.ui.fragment.global

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatDialogFragment

abstract class BaseDialogFragment : MvpAppCompatDialogFragment() {
    abstract val layoutRes: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(layoutRes, container, false)

    open fun onBackPressed(): Boolean = false
}
