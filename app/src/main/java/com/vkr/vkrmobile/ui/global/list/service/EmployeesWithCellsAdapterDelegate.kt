package com.vkr.vkrmobile.ui.global.list.service

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.vkr.vkrmobile.R
import com.vkr.vkrmobile.model.data.net.response.employee.EmployeeWithTimeCellsResponse
import com.vkr.vkrmobile.ui.fragment.service.CellDateAdapterDelegate
import com.vkr.vkrmobile.ui.global.inflate
import kotlinx.android.synthetic.main.employee_with_cells_item.view.*
import java.util.*

class EmployeesWithCellsAdapterDelegate(
    private val onDateClickListener: (Long, String) -> Unit
) : AdapterDelegate<MutableList<EmployeeWithTimeCellsResponse>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ViewHolder(parent.inflate(R.layout.employee_with_cells_item))
    }

    override fun isForViewType(items: MutableList<EmployeeWithTimeCellsResponse>, position: Int): Boolean {
        return true
    }

    override fun onBindViewHolder(
        items: MutableList<EmployeeWithTimeCellsResponse>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as ViewHolder).bind(items[position])
    }

    private inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val adapter: CellDateAdapter by lazy { CellDateAdapter() }

        init {
            with(view) {
                dateCellsRecyclerView.run {
                    layoutManager = GridLayoutManager(context, 3)
                    adapter = this@ViewHolder.adapter
                }
            }
        }

        fun bind(item: EmployeeWithTimeCellsResponse) {
            with(view) {
                adapter.setData(item.timeCells, item.employee.id)
                employeeName.text = item.employee.name
                employeePhoneNumber.text = item.employee.phoneNumber
            }
        }

        private inner class CellDateAdapter : ListDelegationAdapter<MutableList<Date>>() {
            val cellDateAdapterDelegate = CellDateAdapterDelegate { employeeId, date -> onDateClickListener.invoke(employeeId, date) }

            init {
                items = mutableListOf()
                delegatesManager.addDelegate(cellDateAdapterDelegate)
            }

            fun setData(data: List<Date>, employeeId: Long) {
                cellDateAdapterDelegate.employeeId = employeeId
                items.clear()
                items.addAll(data)
                notifyDataSetChanged()
            }
        }
    }
}