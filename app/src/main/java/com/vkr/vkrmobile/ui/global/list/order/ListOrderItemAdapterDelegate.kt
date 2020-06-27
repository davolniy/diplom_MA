package com.vkr.vkrmobile.ui.global.list.cart

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.vkr.vkrmobile.R
import com.vkr.vkrmobile.model.data.net.response.order.OrderItemResponse
import com.vkr.vkrmobile.ui.global.inflate
import com.vkr.vkrmobile.ui.global.loadImage
import kotlinx.android.synthetic.main.order_item_list_item.view.*

class ListOrderItemAdapterDelegate() : AdapterDelegate<MutableList<OrderItemResponse>>() {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ViewHolder(parent.inflate(R.layout.order_item_list_item))
    }

    override fun isForViewType(items: MutableList<OrderItemResponse>, position: Int): Boolean {
        return true
    }

    override fun onBindViewHolder(
        items: MutableList<OrderItemResponse>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as ViewHolder).bind(items[position])
    }

    private inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: OrderItemResponse) {
            with(view) {
                productName.text = item.productData.name
                productLogo.loadImage(
                    url = item.productData.logo,
                    placeholderDrawable = ContextCompat.getDrawable(context, R.drawable.ic_placeholder)
                )
                productCount.text = item.count.toString()
            }
        }
    }
}