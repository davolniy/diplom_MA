package com.vkr.vkrmobile.ui.global.list.product

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.vkr.vkrmobile.R
import com.vkr.vkrmobile.model.data.net.response.cart.CartItemResponse
import com.vkr.vkrmobile.model.data.net.response.company.CompanyWithBranchesResponse
import com.vkr.vkrmobile.model.data.net.response.product.ProductResponse
import com.vkr.vkrmobile.ui.global.inflate
import com.vkr.vkrmobile.ui.global.loadImage
import kotlinx.android.synthetic.main.company_list_item.view.*
import kotlinx.android.synthetic.main.product_list_item.view.*

class ListProductAdapterDelegate(
    private val accentColor: Int,
    private val onAddToCartClickListener: (Long, Long?) -> Unit
) : AdapterDelegate<MutableList<ProductResponse>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ViewHolder(parent.inflate(R.layout.product_list_item))
    }

    override fun isForViewType(items: MutableList<ProductResponse>, position: Int): Boolean {
        return true
    }

    override fun onBindViewHolder(
        items: MutableList<ProductResponse>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as ViewHolder).bind(items[position])
    }

    private inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: ProductResponse) {
            with(view) {
                productName.text = item.name
                productDescription.text = item.description
                productCost.text = String.format(context.getString(R.string.costPlaceHolder), item.cost)
                productLogo.loadImage(
                    url = item.logo,
                    placeholderDrawable = ContextCompat.getDrawable(context, R.drawable.ic_placeholder)
                )

                item.duration?.let {
                    addToCartButton.text = context.getString(R.string.makeRequest)
                }

                addToCartButton.setBackgroundColor(accentColor)
                addToCartButton.setOnClickListener {
                    onAddToCartClickListener.invoke(item.id, item.duration)
                }
            }
        }
    }
}