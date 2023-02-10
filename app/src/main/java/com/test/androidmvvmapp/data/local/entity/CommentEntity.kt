package com.test.androidmvvmapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comment_table")
data class CommentEntity(
    @PrimaryKey val id: Int,
    val body: String,
    val email: String,
    val name: String,
    val postId: Int
)
