package com.test.homework11.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.homework11.Adapters.EmployeeAdapter
import com.test.homework11.Adapters.PostAdapter
import com.test.homework11.Database.getDatabase
import com.test.homework11.Models.Employee
import com.test.homework11.Models.Post
import com.test.homework11.databinding.EmployeeListBinding
import com.test.homework11.databinding.PostListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class EmployeeFragment : Fragment(), CoroutineScope {
    companion object {
        const val EMPLOYEE_KEY = "employee_key_state"
    }
    private lateinit var binding: EmployeeListBinding
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var recycler: RecyclerView

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EmployeeListBinding.inflate(inflater, container, false)

        savedInstanceState?.let {
            binding.employeeList.layoutManager?.onRestoreInstanceState(
                savedInstanceState.getParcelable(
                    EMPLOYEE_KEY
                )
            )
        }
        activity?.title = "Сотрудники"

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireNotNull(this.activity)

        val database = getDatabase(activity.application)

        launch {
            val employeeList = database.employeeDao.getAllEmployees().map {
                it.toEmployee()
            }
            requireActivity().runOnUiThread {
                setList(employeeList)
                val progressBar: ProgressBar = binding.employeeProgressBar
                progressBar.visibility = View.GONE
            }
        }
    }

    private fun setList(employees: List<Employee>) {
        val adapter = EmployeeAdapter(employees as MutableList<Employee>)
        recycler = binding.employeeList
        recycler.adapter = adapter
        layoutManager = LinearLayoutManager(requireContext())
        recycler.layoutManager = layoutManager
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(
            EMPLOYEE_KEY,
            binding.employeeList.layoutManager?.onSaveInstanceState()
        )
    }
}