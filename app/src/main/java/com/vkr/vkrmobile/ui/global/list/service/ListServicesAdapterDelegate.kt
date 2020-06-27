package com.vkr.vkrmobile.ui.global.list.service

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.vkr.vkrmobile.R
import com.vkr.vkrmobile.model.data.net.response.service.ServiceResponse
import com.vkr.vkrmobile.ui.global.inflate
import com.vkr.vkrmobile.ui.global.loadImage
import kotlinx.android.synthetic.main.service_list_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class ListServicesAdapterDelegate() : AdapterDelegate<MutableList<ServiceResponse>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ViewHolder(parent.inflate(R.layout.service_list_item))
    }

    override fun isForViewType(items: MutableList<ServiceResponse>, position: Int): Boolean {
        return true
    }

    override fun onBindViewHolder(
        items: MutableList<ServiceResponse>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as ViewHolder).bind(items[position])
    }

    private inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: ServiceResponse) {
            with(view) {
                serviceId.text = String.format(context.getString(R.string.serviceIdPlaceholder), item.id)
                companyName.text = String.format(context.getString(R.string.serviceCompanyPlaceholder), item.companyName)
                employeeName.text = String.format(context.getString(R.string.serviceEmployeePlaceholder), item.employeeData.name)
                employeePhoneNumber.text = String.format(context.getString(R.string.serviceEmployeePhoneNumberPlaceholder), item.employeeData.phoneNumber)

                val formatDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val formatTime = SimpleDateFormat("HH:mm", Locale.getDefault())
                formatDate.timeZone = TimeZone.getDefault()
                formatTime.timeZone = TimeZone.getDefault()
                val calendar = Calendar.getInstance()
                calendar.time = item.createDate
                serviceCreateDate.text = String.format(context.getString(R.string.serviceCreateDatePlaceholder),
                    "${formatDate.format(calendar.timeInMillis + TimeZone.getDefault().rawOffset)} --- " +
                            "${formatTime.format(calendar.timeInMillis + TimeZone.getDefault().rawOffset)}")

                productName.text = String.format(context.getString(R.string.serviceNamePlaceholder), item.productData.name)
                productDuration.text = String.format(context.getString(R.string.serviceDurationPlaceholder), item.productData.duration)
                productCost.text = String.format(context.getString(R.string.serviceCostPlaceholder), item.productData.cost)

                productLogo.loadImage(
                    url = item.productData.logo,
                    placeholderDrawable = ContextCompat.getDrawable(context, R.drawable.ic_placeholder)
                )
            }
        }
    }

}