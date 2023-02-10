package com.test.androidmvvmapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.androidmvvmapp.data.local.dao.CommentDao
import com.test.androidmvvmapp.data.local.dao.PostDao
import com.test.androidmvvmapp.data.local.entity.CommentEntity
import com.test.androidmvvmapp.data.local.entity.PostEntity

@Database(entities = [PostEntity::class, CommentEntity::class], version = 1)
abstract class PostDatabase: RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "post.db"
    }

    abstract fun postDao(): PostDao

    abstract fun commentsDao(): CommentDao

}