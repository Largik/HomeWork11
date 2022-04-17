package com.test.homework11.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.homework11.Models.Employee
import com.test.homework11.R

class EmployeeAdapter(private val items: MutableList<Employee>) : RecyclerView.Adapter<EmployeeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val rootView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.person_item, parent, false)

        return EmployeeViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.bind(items[position])
    }
}

class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val textNameView: TextView = itemView.findViewById(R.id.name)
    private val textAgeView: TextView = itemView.findViewById(R.id.age)
    private val textWorkExperienceView: TextView = itemView.findViewById(R.id.work_experience)


    @SuppressLint("SetTextI18n")
    fun bind(employee: Employee) {
        textNameView.text = "Name: \n" + employee.name
        textAgeView.text = "Age: \n" + employee.age.toString()
        textWorkExperienceView.text = "Work experience: \n" + employee.workExperience
    }
}