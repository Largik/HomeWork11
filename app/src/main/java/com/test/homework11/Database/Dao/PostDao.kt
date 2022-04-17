package com.test.homework11.Database.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.homework11.Database.Entities.PostEntity

@Dao
interface PostDao {
    @Query("select * from post_table")
    fun getPosts(): List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(vararg position: PostEntity)
}