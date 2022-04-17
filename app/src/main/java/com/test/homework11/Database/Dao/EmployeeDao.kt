package com.test.homework11.Database.Dao

import androidx.room.*
import com.test.homework11.Database.Entities.EmployeeEntity


@Dao
interface EmployeeDao {
    @Query("SELECT * FROM employee_table")
    fun getAllEmployees(): List<EmployeeEntity>
    @Query("SELECT * FROM employee_table WHERE id = :id")
    fun getEmployeeById(id: Long): EmployeeEntity
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmployee(employee: EmployeeEntity)
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateEmployee(employee: EmployeeEntity)
    @Delete
    fun deleteEmployee(employee: EmployeeEntity)
}

