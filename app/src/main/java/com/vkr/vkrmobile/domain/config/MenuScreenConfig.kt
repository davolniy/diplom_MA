package com.vkr.vkrmobile.domain.config

import android.content.Context
import com.vkr.vkrmobile.R
import com.vkr.vkrmobile.domain.menu.CustomMenu
import com.vkr.vkrmobile.domain.menu.CustomMenuItem
import com.vkr.vkrmobile.ui.screens.BottomMenuScreen
import com.vkr.vkrmobile.ui.screens.NewsScreen
import javax.inject.Inject

class MenuScreenConfig @Inject constructor(
    private val globalConfig: GlobalConfig,
    private val context: Context
) {
    private val menuMode = globalConfig.configurationParams.menuViewMode

    private val functions = globalConfig.configurationParams.configurationFunctions

    private val allMenuItems: List<CustomMenuItem>
        get() {
            val menu: MutableCollection<CustomMenuItem> = mutableListOf()

            menu.add(
                CustomMenuItem(
                    title = "Профиль",
                    icon = context.getDrawable(R.drawable.ic_profile)?.apply { setTint(globalConfig.accentColor) },
                    screen = NewsScreen()
                )
            )

            if (functions.catalogs) {
                menu.add(
                    CustomMenuItem(
                        title = "Компании",
                        icon = context.getDrawable(R.drawable.ic_company)?.apply {
                            setTint(
                                globalConfig.accentColor
                            )
                        },
                        screen = NewsScreen()
                    )
                )
                menu.add(
                    CustomMenuItem(
                        title = "Корзина",
                        icon = context.getDrawable(R.drawable.ic_cart)?.apply { setTint(globalConfig.accentColor) },
                        screen = NewsScreen()
                    )
                )
            }

            if (functions.news) {
                menu.add(
                    CustomMenuItem(
                        title = "Новости",
                        icon = context.getDrawable(R.drawable.ic_news)?.apply { setTint(globalConfig.accentColor) },
                        screen = NewsScreen()
                    )
                )
            }

            if (functions.chats) {
                menu.add(
                    CustomMenuItem(
                        title = "Чаты",
                        icon = context.getDrawable(R.drawable.ic_chat)?.apply { setTint(globalConfig.accentColor) },
                        screen = NewsScreen()
                    )
                )
            }

            if (functions.orders) {
                menu.add(
                    CustomMenuItem(
                        title = "Заказы",
                        icon = context.getDrawable(R.drawable.ic_action)?.apply {
                            setTint(
                                globalConfig.accentColor
                            )
                        },
                        screen = NewsScreen()
                    )
                )
            }

            if (functions.requests) {
                menu.add(
                    CustomMenuItem(
                        title = "Заявки",
                        icon = context.getDrawable(R.drawable.ic_employee)?.apply {
                            setTint(
                                globalConfig.accentColor
                            )
                        },
                        screen = NewsScreen()
                    )
                )
            }

            if (functions.services) {
                menu.add(
                    CustomMenuItem(
                        title = "Записи",
                        icon = context.getDrawable(R.drawable.ic_service)?.apply {
                            setTint(
                                globalConfig.accentColor
                            )
                        },
                        screen = NewsScreen()
                    )
                )
            }

            if (functions.reviews) {
                menu.add(
                    CustomMenuItem(
                        title = "Отзывы",
                        icon = context.getDrawable(R.drawable.ic_rating)?.apply {
                            setTint(
                                globalConfig.accentColor
                            )
                        },
                        screen = NewsScreen()
                    )
                )
            }

            return menu.toList()
        }

    val customMenu: CustomMenu
        get() {
            val menuItems: MutableCollection<CustomMenuItem> = mutableListOf()
            val plusItems: MutableCollection<CustomMenuItem> = mutableListOf()

            if (menuMode == "Bottom" && allMenuItems.size > 5) {
                menuItems.addAll(allMenuItems.subList(0, 2))
                menuItems.add(
                    CustomMenuItem(
                        title = "",
                        icon = null,
                        screen = BottomMenuScreen(true)
                    )
                )
                menuItems.addAll(allMenuItems.subList(2, 4))
                plusItems.addAll(allMenuItems.subList(4, allMenuItems.size))
            } else {
                menuItems.addAll(allMenuItems)
            }

            return CustomMenu(menuItems = menuItems.toList(), plusMenuItems = plusItems.toList())
        }

    val plusButtonEnabled = customMenu.plusMenuItems.count() > 0
}