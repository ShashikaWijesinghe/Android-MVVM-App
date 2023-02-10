package com.test.androidmvvmapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post_table")
data class PostEntity(
    @PrimaryKey val id: Int,
    val body: String,
    val title: String,
    val userId: Int
)
