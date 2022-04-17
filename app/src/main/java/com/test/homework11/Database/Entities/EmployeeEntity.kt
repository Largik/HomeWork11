package com.test.homework11.Database.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.homework11.Models.Employee

@Entity(
    tableName = "employee_table"
)
data class EmployeeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val age: Int,
    val workExperience: String
) {
    fun toEmployee(): Employee = Employee(
        name = name,
        age = age,
        workExperience = workExperience
    )
}