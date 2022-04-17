package com.test.homework11.Database.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.homework11.Models.Post

@Entity(
    tableName = "post_table"
)
data class PostEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
) {
    fun toPost(): Post = Post(
        name = name
    )
}