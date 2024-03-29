package com.vkr.vkrmobile.ui.global.list.companies

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.vkr.vkrmobile.R
import com.vkr.vkrmobile.model.data.net.response.company.CompanyWithBranchesResponse
import com.vkr.vkrmobile.ui.global.inflate
import com.vkr.vkrmobile.ui.global.loadImage
import kotlinx.android.synthetic.main.company_cell_item.view.*

class CellsCompaniesAdapterDelegate(
    private val companiesMenuViewMode: String,
    private val onCompanyClickListener: (Long) -> Unit
) : AdapterDelegate<MutableList<CompanyWithBranchesResponse>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ViewHolder(parent.inflate(R.layout.company_cell_item))
    }

    override fun isForViewType(items: MutableList<CompanyWithBranchesResponse>, position: Int): Boolean {
        return companiesMenuViewMode == "Cells"
    }

    override fun onBindViewHolder(
        items: MutableList<CompanyWithBranchesResponse>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as ViewHolder).bind(items[position])
    }

    private inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: CompanyWithBranchesResponse) {
            with(view) {
                companyLayout.setOnClickListener { onCompanyClickListener.invoke(item.parentCompany.id) }
                companyLogo.loadImage(
                    url = item.parentCompany.logo,
                    placeholderDrawable = ContextCompat.getDrawable(context, R.drawable.ic_placeholder)
                )
            }
        }
    }
}