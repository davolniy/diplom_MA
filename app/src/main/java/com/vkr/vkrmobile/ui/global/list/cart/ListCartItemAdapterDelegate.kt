package com.vkr.vkrmobile.ui.global.list.cart

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.vkr.vkrmobile.R
import com.vkr.vkrmobile.model.data.net.response.cart.CartItemResponse
import com.vkr.vkrmobile.ui.global.inflate
import com.vkr.vkrmobile.ui.global.loadImage
import kotlinx.android.synthetic.main.cart_item_list_item.view.*

class ListCartItemAdapterDelegate(
    private val onCountClickListener: (Long, Long, Int) -> Unit
) : AdapterDelegate<MutableList<CartItemResponse>>() {

    var companyId: Long = 0L

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ViewHolder(parent.inflate(R.layout.cart_item_list_item))
    }

    override fun isForViewType(items: MutableList<CartItemResponse>, position: Int): Boolean {
        return true
    }

    override fun onBindViewHolder(
        items: MutableList<CartItemResponse>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as ViewHolder).bind(items[position])
    }

    private inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: CartItemResponse) {
            with(view) {
                productName.text = item.productData.name
                productLogo.loadImage(
                    url = item.productData.logo,
                    placeholderDrawable = ContextCompat.getDrawable(context, R.drawable.ic_placeholder)
                )
                productCount.text = item.count.toString()

                plusCartButton.setOnClickListener { onCountClickListener.invoke(companyId, item.productData.id, item.count + 1) }
                minusCartButton.setOnClickListener { onCountClickListener.invoke(companyId, item.productData.id, item.count - 1) }
            }
        }
    }
}