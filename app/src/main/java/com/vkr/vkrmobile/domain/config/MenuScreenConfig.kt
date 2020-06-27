package com.vkr.vkrmobile.domain.config

import android.content.Context
import com.vkr.vkrmobile.R
import com.vkr.vkrmobile.domain.menu.CustomMenu
import com.vkr.vkrmobile.domain.menu.CustomMenuItem
import com.vkr.vkrmobile.ui.screens.*
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

            if (functions.catalogs) {
                menu.add(
                    CustomMenuItem(
                        title = "Компании",
                        icon = context.getDrawable(R.drawable.ic_company)?.apply {
                            setTint(
                                globalConfig.accentColor
                            )
                        },
                        screen = CompaniesScreen()
                    )
                )
                menu.add(
                    CustomMenuItem(
                        title = "Корзина",
                        icon = context.getDrawable(R.drawable.ic_cart)?.apply { setTint(globalConfig.accentColor) },
                        screen = CartsScreen()
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
                        screen = ChatsScreen()
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
                        screen = OrdersScreen()
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
                menu.add(
                    CustomMenuItem(
                        title = "Оставить заявку",
                        icon = context.getDrawable(R.drawable.ic_add)?.apply {
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
                        screen = ServicesScreen()
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

            menu.add(
                CustomMenuItem(
                    title = "Профиль",
                    icon = context.getDrawable(R.drawable.ic_profile)?.apply { setTint(globalConfig.accentColor) },
                    screen = EditProfileScreen()
                )
            )

            return menu.toList()
        }

    val customMenu: CustomMenu
        get() {
            val menuItems: MutableCollection<CustomMenuItem> = mutableListOf()
            val plusItems: MutableCollection<CustomMenuItem> = mutableListOf()

            val menu = allMenuItems

            if (menuMode == "Bottom" && menu.size > 5) {
                menuItems.addAll(menu.subList(0, 2))
                menuItems.add(
                    CustomMenuItem(
                        title = "",
                        icon = null
                    )
                )
                menuItems.addAll(menu.subList(2, 4))
                plusItems.addAll(menu.subList(4, menu.size))
            } else {
                menuItems.addAll(menu)
            }

            return CustomMenu(menuItems = menuItems.toList(), plusMenuItems = plusItems.toList())
        }

    val plusButtonEnabled = customMenu.plusMenuItems.count() > 0
}