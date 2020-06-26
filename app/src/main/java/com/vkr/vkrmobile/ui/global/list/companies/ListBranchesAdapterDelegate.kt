package com.vkr.vkrmobile.ui.global.list.companies

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.vkr.vkrmobile.R
import com.vkr.vkrmobile.model.data.net.response.company.CompanyResponse
import com.vkr.vkrmobile.ui.global.dp
import com.vkr.vkrmobile.ui.global.inflate
import com.vkr.vkrmobile.ui.global.loadImage
import com.vkr.vkrmobile.ui.global.updatePadding
import kotlinx.android.synthetic.main.company_list_item.view.*

class ListBranchesAdapterDelegate() : AdapterDelegate<MutableList<CompanyResponse>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ViewHolder(parent.inflate(R.layout.company_list_item))
    }

    override fun isForViewType(items: MutableList<CompanyResponse>, position: Int): Boolean {
        return true
    }

    override fun onBindViewHolder(
        items: MutableList<CompanyResponse>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as ViewHolder).bind(items[position])
    }

    private inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: CompanyResponse) {
            with(view) {
                companyLogo.loadImage(
                    url = item.logo,
                    placeholderDrawable = ContextCompat.getDrawable(context, R.drawable.ic_placeholder)
                )
                companyName.text = item.name
                companyReviews.text = String.format(context.getString(R.string.companyScorePlaceHolder), item.reviewScore)
            }
        }
    }

}