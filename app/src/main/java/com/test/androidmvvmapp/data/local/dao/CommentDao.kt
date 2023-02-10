package com.test.androidmvvmapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.androidmvvmapp.data.local.entity.CommentEntity

@Dao
interface CommentDao {
    @Query("SELECT * FROM comment_table where postId is :postId")
    fun getCommentsForPost(postId: Int): List<CommentEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(post: CommentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<CommentEntity>)

    @Query("DELETE FROM comment_table")
    suspend fun deleteAll()
}