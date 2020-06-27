package com.vkr.vkrmobile.ui.fragment.service

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.vkr.vkrmobile.R
import com.vkr.vkrmobile.ui.global.inflate
import kotlinx.android.synthetic.main.date_cell_list_item.view.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class CellDateAdapterDelegate(
    private val onDateClickListener: (Long, String) -> Unit
) : AdapterDelegate<MutableList<Date>>() {

    var employeeId: Long = 0L

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ViewHolder(parent.inflate(R.layout.date_cell_list_item))
    }

    override fun isForViewType(items: MutableList<Date>, position: Int): Boolean {
        return true
    }

    override fun onBindViewHolder(
        items: MutableList<Date>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as ViewHolder).bind(items[position])
    }

    private inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Date) {
            with(view) {
                val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
                val formatForCells = SimpleDateFormat("HH:mm", Locale.getDefault())
                format.timeZone = TimeZone.getDefault()
                val calendar = Calendar.getInstance()
                calendar.time = item
                dateCell.text = formatForCells.format(calendar.timeInMillis + TimeZone.getDefault().rawOffset)
                setOnClickListener { onDateClickListener.invoke(employeeId, format.format(calendar.timeInMillis + TimeZone.getDefault().rawOffset)) }
            }
        }
    }
}