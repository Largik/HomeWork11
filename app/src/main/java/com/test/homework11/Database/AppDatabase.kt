package com.test.homework11.Database

import android.content.Context
import androidx.room.*
import androidx.room.Database
import com.test.homework11.Database.Dao.EmployeeDao
import com.test.homework11.Database.Entities.EmployeeEntity
import com.test.homework11.Database.Entities.PostEntity
import com.test.homework11.Database.Dao.PostDao

@Database (entities = [EmployeeEntity::class, PostEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val postDao: PostDao
    abstract val employeeDao: EmployeeDao
}

private lateinit var INSTANCE: AppDatabase

fun getDatabase(context: Context): AppDatabase {
    synchronized(AppDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "appDB"
            ).build()
        }
    }
    return INSTANCE
}