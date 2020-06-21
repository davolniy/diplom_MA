package com.vkr.vkrmobile.ui.global

import android.R
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.vkr.vkrmobile.model.data.net.ApiError
import com.vkr.vkrmobile.model.data.net.ApiResponse
import com.vkr.vkrmobile.model.data.net.ApiResponseEmpty
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

fun <T> Single<ApiResponse<T>>.fetchData(): Single<T> = flatMap {
    when {
        it.isSuccess == true && it.data != null -> Single.just(it.data)
        it.errorCode != null -> Single.error(ApiError(it.errorCode, it.errorMessage))
        else -> Single.error(Throwable(it.errorMessage))
    }
}

fun Single<ApiResponseEmpty>.fetchResult(): Completable = flatMapCompletable {
    when {
        it.isSuccess == true -> Completable.complete()
        it.errorCode != null -> Completable.error(ApiError(it.errorCode, it.errorMessage))
        else -> Completable.error(Throwable(it.errorMessage))
    }
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun FloatingActionButton.setColorView(accentColor: Int){
    val states = arrayOf(
        intArrayOf(R.attr.state_checked),
        intArrayOf(-R.attr.state_checked)
    )

    val colors = intArrayOf(accentColor, accentColor)

    val defaultHintTextColor = ColorStateList(states, colors)
    defaultHintTextColor.let {
        backgroundTintList = it
    }
}

fun BottomNavigationView.setColorView(accentColor: Int){
    val states = arrayOf(
        intArrayOf(R.attr.state_checked),
        intArrayOf(-R.attr.state_checked)
    )

    val colors = intArrayOf(
        accentColor,
        ContextCompat.getColor(context, R.color.darker_gray)
    )

    val defaultHintTextColor = ColorStateList(states, colors)
    defaultHintTextColor.let {
        itemTextColor = it
        itemIconTintList = it
    }
}

fun ImageView.loadImage(
    url: String?,
    placeholderDrawable: Drawable? = null,
    resourceReadyCallback: (Bitmap?) -> Unit = { }
) {
    Glide.with(context)
        .asBitmap()
        .load(url)
        .into(object : BitmapImageViewTarget(this) {
            override fun onLoadStarted(placeholder: Drawable?) {
                super.onLoadStarted(placeholder)
                if (placeholderDrawable != null) {
                    setImageDrawable(placeholderDrawable)
                }
            }

            override fun onLoadFailed(errorDrawable: Drawable?) {
                super.onLoadFailed(errorDrawable)
                if (placeholderDrawable != null) {
                    setImageDrawable(placeholderDrawable)
                }
            }

            override fun setResource(resource: Bitmap?) {
                if (resource != null) {
                    setImageBitmap(resource)
                }
                resourceReadyCallback.invoke(resource)
            }
        })
}