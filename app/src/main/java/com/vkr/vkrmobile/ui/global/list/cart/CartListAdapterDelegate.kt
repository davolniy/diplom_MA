package com.vkr.vkrmobile.ui.global.list.cart

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.vkr.vkrmobile.R
import com.vkr.vkrmobile.model.data.net.response.cart.CartItemResponse
import com.vkr.vkrmobile.model.data.net.response.cart.CartResponse
import com.vkr.vkrmobile.ui.global.inflate
import kotlinx.android.synthetic.main.cart_list_item.view.*

class CartListAdapterDelegate(
    private val accentColor: Int,
    private val onOrderButtonClickListener: (Long) -> Unit,
    private val onCountClickListener: (Long, Long, Int) -> Unit
) : AdapterDelegate<MutableList<CartResponse>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ViewHolder(parent.inflate(R.layout.cart_list_item))
    }

    override fun isForViewType(items: MutableList<CartResponse>, position: Int): Boolean {
        return true
    }

    override fun onBindViewHolder(
        items: MutableList<CartResponse>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as ViewHolder).bind(items[position])
    }

    private inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val adapter: CartItemsAdapter by lazy { CartItemsAdapter() }

        init {
            with(view) {
                cartItemsRecyclerView.run {
                    layoutManager = LinearLayoutManager(context)
                    adapter = this@ViewHolder.adapter
                }
            }
        }

        fun bind(item: CartResponse) {
            with(view) {
                adapter.setData(item.cartItems, item.companyId)
                orderButton.setBackgroundColor(accentColor)
                orderButton.setOnClickListener { onOrderButtonClickListener.invoke(item.id) }
                companyName.text = item.companyName
            }
        }

        private inner class CartItemsAdapter : ListDelegationAdapter<MutableList<CartItemResponse>>() {
            val listCartItemAdapterDelegate = ListCartItemAdapterDelegate(
                onCountClickListener
            )

            init {
                items = mutableListOf()
                delegatesManager.addDelegate(listCartItemAdapterDelegate)
            }

            fun setData(data: List<CartItemResponse>, companyId: Long) {
                listCartItemAdapterDelegate.companyId = companyId
                items.clear()
                items.addAll(data)
                notifyDataSetChanged()
            }
        }
    }
}