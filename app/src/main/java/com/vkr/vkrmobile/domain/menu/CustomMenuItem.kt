package com.vkr.vkrmobile.domain.menu

import android.graphics.drawable.Drawable
import ru.terrakok.cicerone.android.support.SupportAppScreen

class CustomMenuItem(
    val title: String,
    val icon: Drawable?,
    val screen: SupportAppScreen? = null,
    val action: (() -> Unit)? = null
)