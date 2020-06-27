package com.vkr.vkrmobile.ui.global.list.cart

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.vkr.vkrmobile.R
import com.vkr.vkrmobile.model.data.net.response.order.OrderItemResponse
import com.vkr.vkrmobile.model.data.net.response.order.OrderResponse
import com.vkr.vkrmobile.ui.global.inflate
import kotlinx.android.synthetic.main.order_list_item.view.*

class OrderListAdapterDelegate() : AdapterDelegate<MutableList<OrderResponse>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ViewHolder(parent.inflate(R.layout.order_list_item))
    }

    override fun isForViewType(items: MutableList<OrderResponse>, position: Int): Boolean {
        return true
    }

    override fun onBindViewHolder(
        items: MutableList<OrderResponse>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as ViewHolder).bind(items[position])
    }

    private inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val adapter: OrderItemsAdapter by lazy { OrderItemsAdapter() }

        init {
            with(view) {
                orderItemsRecyclerView.run {
                    layoutManager = LinearLayoutManager(context)
                    adapter = this@ViewHolder.adapter
                }
            }
        }

        fun bind(item: OrderResponse) {
            with(view) {
                adapter.setData(item.items)
                companyName.text = item.companyName
            }
        }

        private inner class OrderItemsAdapter : ListDelegationAdapter<MutableList<OrderItemResponse>>() {

            init {
                items = mutableListOf()
                delegatesManager.addDelegate(ListOrderItemAdapterDelegate())
            }

            fun setData(data: List<OrderItemResponse>) {
                items.clear()
                items.addAll(data)
                notifyDataSetChanged()
            }
        }
    }
}