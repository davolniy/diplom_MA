package com.vkr.vkrmobile.ui.global.list.chat

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.vkr.vkrmobile.R
import com.vkr.vkrmobile.model.data.net.response.chat.ChatResponse
import com.vkr.vkrmobile.ui.global.inflate
import com.vkr.vkrmobile.ui.global.loadImage
import kotlinx.android.synthetic.main.chat_list_item.view.*

class ListChatsAdapterDelegate() : AdapterDelegate<MutableList<ChatResponse>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ViewHolder(parent.inflate(R.layout.chat_list_item))
    }

    override fun isForViewType(items: MutableList<ChatResponse>, position: Int): Boolean {
        return true
    }

    override fun onBindViewHolder(
        items: MutableList<ChatResponse>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as ViewHolder).bind(items[position])
    }

    private inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: ChatResponse) {
            with(view) {
                chatLogo.loadImage(
                    url = item.logo,
                    placeholderDrawable = ContextCompat.getDrawable(context, R.drawable.ic_placeholder)
                )
                chatName.text = item.name
                lastMesssage.text = item.lastMessage.text
            }
        }
    }

}