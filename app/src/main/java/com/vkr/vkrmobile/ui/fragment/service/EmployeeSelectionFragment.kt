package com.vkr.vkrmobile.ui.fragment.service

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.vkr.vkrmobile.R
import com.vkr.vkrmobile.di.AppScopes
import com.vkr.vkrmobile.domain.config.GlobalConfig
import com.vkr.vkrmobile.model.data.net.response.employee.EmployeeWithTimeCellsResponse
import com.vkr.vkrmobile.presentation.service.EmployeeSelectionPresenter
import com.vkr.vkrmobile.presentation.service.EmployeeSelectionView
import com.vkr.vkrmobile.ui.fragment.global.BaseFragment
import com.vkr.vkrmobile.ui.global.list.service.EmployeesWithCellsAdapterDelegate
import kotlinx.android.synthetic.main.employee_selection_fragment.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import toothpick.Toothpick
import toothpick.config.Module
import java.util.*
import javax.inject.Inject

class EmployeeSelectionFragment : BaseFragment(), EmployeeSelectionView {

    companion object {
        private const val COMPANY_ID_ARGUMENT = "company_id_argument"
        private const val PRODUCT_ID_ARGUMENT = "product_id_argument"
        private const val SELECTED_DATE_ARGUMENT = "selected_date_argument"

        fun newInstance(companyId: Long, productId: Long, selectedDate: Long) : Fragment {
            return EmployeeSelectionFragment().apply {
                arguments = Bundle().apply {
                    putLong(COMPANY_ID_ARGUMENT, companyId)
                    putLong(PRODUCT_ID_ARGUMENT, productId)
                    putLong(SELECTED_DATE_ARGUMENT, selectedDate)
                }
            }
        }
    }

    private val companyId get() = arguments?.getLong(COMPANY_ID_ARGUMENT) ?: 0L
    private val productId get() = arguments?.getLong(PRODUCT_ID_ARGUMENT) ?: 0L
    private val selectedDate get() = arguments?.getLong(SELECTED_DATE_ARGUMENT) ?: 0L

    override val layoutRes: Int
        get() = R.layout.employee_selection_fragment

    @InjectPresenter
    lateinit var presenter: EmployeeSelectionPresenter

    @Inject
    lateinit var globalConfig: GlobalConfig

    private val employeesAdapter by lazy { EmployeesAdapter() }

    @ProvidePresenter
    fun providePresenter(): EmployeeSelectionPresenter = Toothpick
        .openScopes(AppScopes.MAIN_ACTIVITY_SCOPE, AppScopes.EMPLOYEE_SELECTION_SCOPE)
        .apply {
            installModules(object : Module() {
                init {
                    val initParams = EmployeeSelectionPresenter.InitParams(companyId, productId, selectedDate)
                    bind(EmployeeSelectionPresenter.InitParams::class.java).toInstance(initParams)
                }
            })
        }
        .getInstance(EmployeeSelectionPresenter::class.java)

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Toothpick.openScopes(AppScopes.MAIN_ACTIVITY_SCOPE, AppScopes.EMPLOYEE_SELECTION_SCOPE).let { Toothpick.inject(this, it) }
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        Toothpick.closeScope(AppScopes.EMPLOYEE_SELECTION_SCOPE)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (globalConfig.configurationParams.menuViewMode == "Top") {
            navigationToolBarButton.run {
                visibility = View.VISIBLE
                setOnClickListener { presenter.onBackPressed() }
            }
        }

        toolbarLayout.run {
            setBackgroundColor(globalConfig.accentColor)
        }

        employeesRecyclerView.run {
            layoutManager = LinearLayoutManager(context)
            adapter = this@EmployeeSelectionFragment.employeesAdapter
        }
    }

    override fun setData(data: List<EmployeeWithTimeCellsResponse>) {
        employeesAdapter.setData(data)
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            employeesSwipeRefreshLayout.isEnabled = show
            employeesSwipeRefreshLayout.isRefreshing = show
        } else {
            employeesSwipeRefreshLayout.isRefreshing = show
            employeesSwipeRefreshLayout.isEnabled = show
        }
    }

    private inner class EmployeesAdapter() : ListDelegationAdapter<MutableList<EmployeeWithTimeCellsResponse>>() {
        init {
            items = mutableListOf()
            delegatesManager
                .addDelegate(EmployeesWithCellsAdapterDelegate { employeeId, date -> presenter.onDateClick(employeeId, date) })
        }

        fun setData(data: List<EmployeeWithTimeCellsResponse>) {
            items.clear()
            items.addAll(data)
            notifyDataSetChanged()
        }
    }
}