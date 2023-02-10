package com.test.androidmvvmapp.domain.repository

import com.test.androidmvvmapp.domain.model.Comment
import com.test.androidmvvmapp.domain.model.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    suspend fun getPosts(): Flow<List<Post>>

    suspend fun getCommentsForPost(postId: Int): Flow<List<Comment>>
}