package com.vkr.vkrmobile.ui.global.list.menu

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.vkr.vkrmobile.R
import com.vkr.vkrmobile.domain.menu.CustomMenuItem
import com.vkr.vkrmobile.ui.global.inflate
import kotlinx.android.synthetic.main.menu_row_item.view.*
import ru.terrakok.cicerone.android.support.SupportAppScreen

class MenuRowAdapterDelegate(
    private val onClickListener: (SupportAppScreen) -> Unit
) : AdapterDelegate<MutableList<CustomMenuItem>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ViewHolder(parent.inflate(R.layout.menu_row_item))
    }

    override fun isForViewType(items: MutableList<CustomMenuItem>, position: Int): Boolean {
        return true
    }

    override fun onBindViewHolder(
        items: MutableList<CustomMenuItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as ViewHolder).bind(items[position] as CustomMenuItem)
    }

    private inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: CustomMenuItem) {
            with(view) {
                menuRowIcon.setImageDrawable(item.icon)
                menuRowTitle.text = item.title
            }

            view.setOnClickListener { item.screen?.run { onClickListener.invoke(item.screen) } }
        }
    }

}