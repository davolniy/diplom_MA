package com.vkr.vkrmobile.ui.global.list.companies

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.vkr.vkrmobile.R
import com.vkr.vkrmobile.model.data.net.response.company.CompanyResponse
import com.vkr.vkrmobile.model.data.net.response.company.CompanyWithBranchesResponse
import com.vkr.vkrmobile.ui.global.inflate
import com.vkr.vkrmobile.ui.global.loadImage
import kotlinx.android.synthetic.main.company_expandable_item.view.*

class ExpandableListCompaniesAdapterDelegate(
    private val companiesMenuViewMode: String
) : AdapterDelegate<MutableList<CompanyWithBranchesResponse>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ViewHolder(parent.inflate(R.layout.company_expandable_item))
    }

    override fun isForViewType(items: MutableList<CompanyWithBranchesResponse>, position: Int): Boolean {
        return companiesMenuViewMode == "Expandable"
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
        private val adapter: BranchAdapter by lazy { BranchAdapter() }

        init {
            with(view) {
                branchesRecyclerView.run {
                    layoutManager = LinearLayoutManager(context)
                    adapter = this@ViewHolder.adapter
                }
            }
        }

        fun bind(item: CompanyWithBranchesResponse) {
            with(view) {
                adapter.setData(item.branches)
                companyLogo.loadImage(
                    url = item.parentCompany.logo,
                    placeholderDrawable = ContextCompat.getDrawable(context, R.drawable.ic_placeholder)
                )
                companyName.text = item.parentCompany.name
                companyReviews.text = String.format(context.getString(R.string.companyScorePlaceHolder), item.parentCompany.reviewScore)

                expandIcon.setOnClickListener {
                    if (branchesRecyclerView.visibility == View.GONE) {
                        expandIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_arrow_top))
                        branchesRecyclerView.visibility = View.VISIBLE
                    } else {
                        expandIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_arrow_bottom))
                        branchesRecyclerView.visibility = View.GONE
                    }
                }
            }
        }

        private inner class BranchAdapter : ListDelegationAdapter<MutableList<CompanyResponse>>() {
            init {
                items = mutableListOf()
                delegatesManager.addDelegate(ListBranchesAdapterDelegate())
            }

            fun setData(data: List<CompanyResponse>) {
                items.clear()
                items.addAll(data)
                notifyDataSetChanged()
            }
        }
    }
}